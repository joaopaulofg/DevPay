package com.joaopaulofg.cadastroapp.repository;

import com.joaopaulofg.cadastroapp.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    UserDetails findByTelefone(String telefone);
}
