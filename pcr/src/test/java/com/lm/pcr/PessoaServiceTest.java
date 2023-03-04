package com.lm.pcr;

import com.lm.pcr.models.Pessoa;
import com.lm.pcr.repository.PessoaRepository;
import com.lm.pcr.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PessoaServiceTest {

    public static final long ID = 1L;
    public static final String NOME = "personName10";
    public static final Integer POSICAO = 10;

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

    void criarPessoa(){
        when(personRepository.save(any())).thenReturn(person);
        Pessoa response = personService.create(person);

        assertNotNull(response);
        assertEquals(Pessoa.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME.toUpperCase(), response.getNome());
        assertEquals(POSICAO, response.getPosicao());
    }


    private void startPerson(){
        person = new Pessoa(1L,"João",20, 10);
    }
}
