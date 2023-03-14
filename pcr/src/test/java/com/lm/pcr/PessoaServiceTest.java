package com.lm.pcr;

import com.lm.pcr.entity.Pessoa;
import com.lm.pcr.repository.PessoaRepository;
import com.lm.pcr.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PessoaServiceTest {

    public static final long ID = 1L;
    public static final String NOME = "João Test";

    public static final Integer IDADE = 20;
    public static final Integer POSICAO999 = 999;
    public static final Integer POSICAO2 = 2;

    @InjectMocks
    private PessoaService personService;

    @Mock
    private PessoaRepository personRepository;

    @Mock
    private ModelMapper modelMapper;

    private Pessoa person;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startPerson();
    }

    @Test
    void criarPrimeiraPessoaNoBDComSucesso(){
        when(personRepository.save(any())).thenReturn(person);
        when(personRepository.menorPosicao()).thenReturn(null);

        Pessoa response = personService.create(person);

        assertNotNull(response);
        assertEquals(Pessoa.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(IDADE, response.getIdade());
        assertEquals(POSICAO999, response.getPosicao());
    }

    @Test
    void criarPessoaComSucessoNoBDJaPopulado(){
        when(personRepository.save(any())).thenReturn(person);
        when(personRepository.menorPosicao()).thenReturn(3);

        Pessoa response = personService.create(person);

        assertNotNull(response);
        assertEquals(Pessoa.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(IDADE, response.getIdade());
        assertEquals(POSICAO2, response.getPosicao());
    }


    private void startPerson(){
        person = new Pessoa(1L,"João Test",20, 0);
    }
}
