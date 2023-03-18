package com.lm.pcr.service;

import com.lm.pcr.dto.PessoaDTO;
import com.lm.pcr.entity.Pessoa;
import com.lm.pcr.repository.PessoaRepository;
import com.lm.pcr.service.exceptions.ObjectNotFoundException;
import com.telesign.MessagingClient;
import com.telesign.RestClient;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    PessoaRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PhoneMessage phoneMessage;

    int tamanhoMaxFila = 1000;

    public Page<PessoaDTO> findAll(Integer page, Integer size, String orderBy, String direction) {
        Pageable pageable =  PageRequest.of(page, size, Sort.Direction.valueOf(direction),orderBy);
        Page<Pessoa> pessoaPage = repository.findAll(pageable);
        return pessoaPage.map(x -> new PessoaDTO(x));
    }

    public Pessoa findById(Long id) {
        Optional<Pessoa> pessoaOptional = repository.findById(id);
        return pessoaOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public Pessoa create(PessoaDTO dto) {
        Integer menorPosicao = repository.menorPosicao();
        if(menorPosicao == null){
            dto.setPosicao(tamanhoMaxFila);
            sendPhoneMessage(dto);
            return repository.save(modelMapper.map(dto, Pessoa.class));
        }
        if(menorPosicao != null && menorPosicao > 1){
            dto.setPosicao(menorPosicao - 1);
            sendPhoneMessage(dto);
            return repository.save(modelMapper.map(dto, Pessoa.class));
        }
        if(repository.count() < tamanhoMaxFila){
            reorganizarOrdemDaFila();
            menorPosicao = repository.menorPosicao();
            dto.setPosicao(menorPosicao - 1);
            sendPhoneMessage(dto);
            return repository.save(modelMapper.map(dto, Pessoa.class));
        }
        throw new IllegalStateException("Lista cheia! Remova para poder adicionar mais. Tamanho máximo da fila: "+ tamanhoMaxFila);
    }

    private void reorganizarOrdemDaFila() {
        List<Pessoa> pessoas = repository.findAll();
        pessoas.sort((p2, p1) -> Integer.compare(p1.getPosicao(), p2.getPosicao()));
        int i = tamanhoMaxFila;
        for(Pessoa p : pessoas){
            System.out.println("pessoa "+p.getId());
            p.setPosicao(i);
            i = i - 1;
            repository.save(modelMapper.map(p, Pessoa.class));
        }
    }

    public Object udpate(PessoaDTO objDto) {
        findById(objDto.getId());
        return repository.save(modelMapper.map(objDto, Pessoa.class));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void sendPhoneMessage(PessoaDTO objDto){

        objDto.setPosicao(objDto.getPosicao());

        PhoneMessage userPhoneMessage = new PhoneMessage(
                objDto.getPhoneNumber(),
                objDto.getNome() + ", Posição na fila: " + objDto.getPosicao());

        System.out.println("customerID "+phoneMessage.getCustomerId());

        try {
            MessagingClient messagingClient = new MessagingClient(
                    phoneMessage.getCustomerId(),
                    phoneMessage.getApiKey(),
                    phoneMessage.getRestEndpoint()
            );
            RestClient.TelesignResponse telesignResponse = messagingClient.message(
                    userPhoneMessage.getPhoneNumber(),
                    userPhoneMessage.getMessage(),
                    phoneMessage.getMessageType(),
                    null
            );
            System.out.println(telesignResponse.json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void testEnvironmentVariable() {
        System.out.println("Test EnvironmentVariable restEndpoint: ".concat(phoneMessage.getRestEndpoint()));
    }
}
