package com.lm.pcr.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lm.pcr.entity.Pessoa;

public class PessoaDTO {

    private Long id;
    private String nome;
    private int idade;
    private int posicao;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String phoneNumber;

    public PessoaDTO (Pessoa p){
        this.id = p.getId();
        this.nome = p.getNome();
        this.idade = p.getIdade();
        this.posicao = p.getPosicao();
    }
    
	public PessoaDTO() {
	}

	public PessoaDTO(Long id, String nome, int idade, int posicao) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.posicao = posicao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
