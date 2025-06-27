package com.joaopaulofg.cadastroapp.repository;

import com.joaopaulofg.cadastroapp.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
