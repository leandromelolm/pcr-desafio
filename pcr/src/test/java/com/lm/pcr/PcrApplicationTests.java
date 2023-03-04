package com.lm.pcr;

import com.lm.pcr.models.Pessoa;
import com.lm.pcr.repository.PessoaRepository;
import com.lm.pcr.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class PcrApplicationTests {

	@InjectMocks
	private PessoaService personService;

	@Mock
	private PessoaRepository personRepository;

	private Pessoa person;


	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startPerson();
	}


	private void startPerson(){
		person = new Pessoa(1L,"João",20, 10);


	}
}
