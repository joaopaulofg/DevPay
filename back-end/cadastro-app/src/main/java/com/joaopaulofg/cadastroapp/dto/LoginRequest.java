package com.joaopaulofg.cadastroapp.dto;

public record LoginRequest(
        String phoneNumber,
        String password
) {
}
