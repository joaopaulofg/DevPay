package com.joaopaulofg.cadastroapp.mapper;

import com.joaopaulofg.cadastroapp.dto.UsuarioRequest;
import com.joaopaulofg.cadastroapp.dto.UsuarioResponse;
import com.joaopaulofg.cadastroapp.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario convertUsuarioRequestToUsuario(UsuarioRequest usuarioRequest);

    UsuarioResponse convertUsuarioToUsuarioResponse(Usuario usuario);
}
