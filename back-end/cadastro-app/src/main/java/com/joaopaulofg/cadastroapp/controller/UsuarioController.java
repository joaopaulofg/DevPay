package com.joaopaulofg.cadastroapp.controller;

import com.joaopaulofg.cadastroapp.dto.UsuarioRequest;
import com.joaopaulofg.cadastroapp.dto.UsuarioResponse;
import com.joaopaulofg.cadastroapp.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponse> createUser(@RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse newUser = usuarioService.criarUsuario(usuarioRequest);
        if (newUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(newUser,  HttpStatus.CREATED);
        }
    }

}
