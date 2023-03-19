package com.lm.pcr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lm.pcr.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Pessoa p")
    Page<Pessoa> findAll(Pageable pageable);

    @Query("SELECT min(p.posicao) FROM Pessoa p")
    Integer menorPosicao();

}