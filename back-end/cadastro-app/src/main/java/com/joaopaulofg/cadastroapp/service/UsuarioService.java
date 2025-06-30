package com.joaopaulofg.cadastroapp.service;

import com.joaopaulofg.cadastroapp.dto.UsuarioRequest;
import com.joaopaulofg.cadastroapp.dto.UsuarioResponse;
import com.joaopaulofg.cadastroapp.entity.Usuario;
import com.joaopaulofg.cadastroapp.mapper.UsuarioMapper;
import com.joaopaulofg.cadastroapp.repository.UsuarioRepository;
import com.joaopaulofg.cadastroapp.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository UsuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final UsuarioRepository usuarioRepository;

//    public UsuarioService(UsuarioRepository UsuarioRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
//                          TokenService tokenService, UsuarioRepository usuarioRepository) {
//        this.UsuarioRepository = UsuarioRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//        this.tokenService = tokenService;
//        this.usuarioRepository = usuarioRepository;
//    }

    public UsuarioResponse criarUsuario(UsuarioRequest UsuarioRequest) {
        Usuario newUsuario = UsuarioMapper.INSTANCE.convertUsuarioRequestToUsuario(UsuarioRequest);
        String ecryptedPassword = passwordEncoder.encode(newUsuario.getPassword());
        newUsuario.setSenha(ecryptedPassword);
        UsuarioRepository.save(newUsuario);
        System.out.println(newUsuario);
        return UsuarioMapper.INSTANCE.convertUsuarioToUsuarioResponse(newUsuario);
    }

    public String login(String telefone, String senha) {
        var user = usuarioRepository.findByTelefone(telefone);

        if (user == null) {
            throw new RuntimeException("User not found");
        } else {
            try {
                var usernamePassword = new UsernamePasswordAuthenticationToken(telefone, senha);
                var auth = authenticationManager.authenticate(usernamePassword);
                var userFound  = (Usuario) auth.getPrincipal();
                return tokenService.generateToken(userFound.getId(), userFound.getTelefone());
            } catch (RuntimeException e) {
                return "Falha na autenticação: " + e.getMessage();
            }
        }
    }
}
