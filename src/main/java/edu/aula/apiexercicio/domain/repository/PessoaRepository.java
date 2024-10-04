package edu.aula.apiexercicio.domain.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.aula.apiexercicio.domain.model.Pessoa;

import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,UUID>{
    
    Optional<Pessoa> findByCpf(String cpf);

}
