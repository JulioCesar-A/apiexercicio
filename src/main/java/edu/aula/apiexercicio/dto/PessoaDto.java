package edu.aula.apiexercicio.dto;

import java.util.UUID;

public record PessoaDto(UUID id, String nome, String cpf, int idade) {
    
}
