package edu.aula.apiexercicio.service;

import org.springframework.stereotype.Service;

import edu.aula.apiexercicio.domain.model.Pessoa;
import edu.aula.apiexercicio.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;
import java.util.Optional;


@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    
    public Optional<Pessoa> findById(UUID id) {
        return pessoaRepository.findById(id);
    }
    

    public Pessoa create(Pessoa pessoaToCreate){

        if (pessoaToCreate.getId() != null && pessoaRepository.existsById(pessoaToCreate.getId())) {
            throw new IllegalArgumentException("Esse ID de Pessoa já existe");            
        }

        Optional<Pessoa> pessoaExistente = pessoaRepository.findByCpf(pessoaToCreate.getCpf());
        if (pessoaExistente.isPresent()){
            throw new IllegalArgumentException("Uma pessoa com o mesmo cpf já existe");
        }

        return pessoaRepository.save(pessoaToCreate);

    }

    
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }
}
