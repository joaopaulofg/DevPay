package com.joaopaulofg.cadastroapp.dto;

import java.util.UUID;

public record UsuarioResponse(
        Long id,
        String nome,
        String telefone
) {
}
