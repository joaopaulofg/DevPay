package com.joaopaulofg.cadastroapp.dto;

public record UsuarioRequest(
        String nome,
        String sobrenome,
        String cpf,
        String telefone,
        String senha,
        Double renda
) {
}
