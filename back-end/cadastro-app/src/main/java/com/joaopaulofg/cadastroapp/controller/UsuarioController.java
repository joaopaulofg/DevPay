package com.joaopaulofg.cadastroapp.controller;

import com.joaopaulofg.cadastroapp.dto.LoginRequest;
import com.joaopaulofg.cadastroapp.dto.LoginResponse;
import com.joaopaulofg.cadastroapp.dto.UsuarioRequest;
import com.joaopaulofg.cadastroapp.dto.UsuarioResponse;
import com.joaopaulofg.cadastroapp.entity.Usuario;
import com.joaopaulofg.cadastroapp.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = usuarioService.login(loginRequest.telefone(), loginRequest.senha());
        return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<Usuario> me(@AuthenticationPrincipal UserDetails userDetails) {
        var user = (Usuario) userDetails;
        return ResponseEntity.ok(user);
    }

    @GetMapping("/teste")
    public ResponseEntity<String> me() {
        return ResponseEntity.ok("Teste");
    }

}
