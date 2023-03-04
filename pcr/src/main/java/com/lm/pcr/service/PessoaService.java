package com.lm.pcr.service;

import com.lm.pcr.dto.PessoaDTO;
import com.lm.pcr.models.Pessoa;
import com.lm.pcr.repository.PessoaRepository;
import com.lm.pcr.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    PessoaRepository repository;

    @Autowired
    ModelMapper modelMapper;

    public Page<PessoaDTO> findAll(Integer page, Integer size, String orderBy, String direction) {
        Pageable pageable =  PageRequest.of(page, size, Sort.Direction.valueOf(direction),orderBy);
        Page<Pessoa> pessoaPage = repository.findAll(pageable);
        return pessoaPage.map(x -> new PessoaDTO(x));
    }

    public Pessoa findById(Long id) {
        Optional<Pessoa> pessoaOptional = repository.findById(id);
        return pessoaOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public Pessoa create(Pessoa obj) {
        int menorPosicao = repository.menorPosicao();
        obj.setPosicao(menorPosicao - 1);

        return repository.save(modelMapper.map(obj, Pessoa.class));
    }

    public Object udpate(PessoaDTO objDto) {
        findById(objDto.getId());
        return repository.save(modelMapper.map(objDto, Pessoa.class));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
