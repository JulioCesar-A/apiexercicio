package edu.aula.apiexercicio.controller;

import org.springframework.http.ResponseEntity;
import edu.aula.apiexercicio.dto.PessoaDto;
import edu.aula.apiexercicio.domain.model.*;
import edu.aula.apiexercicio.service.PessoaService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;



    @PostMapping
    public ResponseEntity<?> createPessoa(@RequestBody PessoaDto pessoaDto) {
        try {

            Pessoa pessoa = new Pessoa();
            BeanUtils.copyProperties(pessoaDto, pessoa);
            Pessoa pessoaSalva = pessoaService.create(pessoa);
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);

        } catch (IllegalArgumentException e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            
        }
    }
    

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas(){
        List<Pessoa> allPessoas = pessoaService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allPessoas);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPessoaById(@PathVariable UUID id){
        
        Optional<Pessoa> pessoaEncontrada = pessoaService.findById(id);
        if(pessoaEncontrada.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(pessoaEncontrada.get());
    
    }
}
