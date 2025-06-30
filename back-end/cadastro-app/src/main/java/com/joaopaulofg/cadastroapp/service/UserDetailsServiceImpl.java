package com.joaopaulofg.cadastroapp.service;

import com.joaopaulofg.cadastroapp.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String telefone) throws UsernameNotFoundException {
        UserDetails user = usuarioRepository.findByTelefone(telefone);
        if (user == null) {
            throw new RuntimeException("User not found");
        } else {
            return user;
        }
    }
}
