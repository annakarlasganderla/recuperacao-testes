package com.haroldo.minhasfinancas.service;

import com.haroldo.minhasfinancas.model.entity.Usuario;

import java.util.Optional;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	boolean validarEmail(String email);

	Optional<Usuario> obterPorId(Long id);
	
}
