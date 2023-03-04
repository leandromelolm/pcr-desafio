package com.lm.pcr.dto;

import com.lm.pcr.models.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private Long id;
    private String nome;
    private int idade;
    private int posicao;

    public PessoaDTO (Pessoa p){
        this.id = p.getId();
        this.nome = p.getNome();
        this.idade = p.getIdade();
        this.posicao = p.getPosicao();
    }
}
