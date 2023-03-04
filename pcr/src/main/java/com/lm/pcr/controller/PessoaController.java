package com.lm.pcr.controller;

import com.lm.pcr.dto.PessoaDTO;
import com.lm.pcr.models.Pessoa;
import com.lm.pcr.service.PessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value="/pessoas")
public class PessoaController {

    @Autowired
    PessoaService service;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<Page<PessoaDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value="orderBy", defaultValue="posicao") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction){
        Page<PessoaDTO> pessoaDTOPage = service.findAll(page, size, orderBy, direction);
        return ResponseEntity.ok().body(pessoaDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> getOnePessoa(@PathVariable("id") Long id){
        Pessoa pessoa = service.findById(id);
        PessoaDTO pessoaDTO = modelMapper.map(pessoa, PessoaDTO.class);
        return ResponseEntity.ok().body(pessoaDTO);
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody Pessoa obj){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(service.create(obj).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody PessoaDTO objDto){
        objDto.setId(id);
        return ResponseEntity.ok().body(modelMapper.map(service.udpate(objDto), PessoaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Pessoa pessoa = service.findById(id);
        service.delete(pessoa.getId());
        return ResponseEntity.status(HttpStatus.OK).body("person "+pessoa.getNome()+" deleted successfully.");
    }

}
