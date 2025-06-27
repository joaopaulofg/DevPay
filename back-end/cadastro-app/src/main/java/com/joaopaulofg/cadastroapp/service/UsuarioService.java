package com.joaopaulofg.cadastroapp.service;

import com.joaopaulofg.cadastroapp.dto.UsuarioRequest;
import com.joaopaulofg.cadastroapp.dto.UsuarioResponse;
import com.joaopaulofg.cadastroapp.entity.Usuario;
import com.joaopaulofg.cadastroapp.mapper.UsuarioMapper;
import com.joaopaulofg.cadastroapp.repository.UsuarioRepository;
import com.joaopaulofg.cadastroapp.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository UsuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository UsuarioRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                          TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.UsuarioRepository = UsuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse criarUsuario(UsuarioRequest UsuarioRequest) {
        Usuario newUsuario = UsuarioMapper.INSTANCE.convertUsuarioRequestToUsuario(UsuarioRequest);
        String ecryptedPassword = passwordEncoder.encode(newUsuario.getPassword());
        newUsuario.setSenha(ecryptedPassword);
        UsuarioRepository.save(newUsuario);
        System.out.println(newUsuario);
        return UsuarioMapper.INSTANCE.convertUsuarioToUsuarioResponse(newUsuario);
    }




}
