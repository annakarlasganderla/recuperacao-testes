package com.haroldo.minhasfinancas.services.impl;

import com.haroldo.minhasfinancas.model.entity.Usuario;
import com.haroldo.minhasfinancas.model.repository.UsuarioRepository;
import com.haroldo.minhasfinancas.service.UsuarioService;
import com.haroldo.minhasfinancas.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTests {

    @Mock
    private UsuarioRepository usuarioRepository;

    UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    public void setup(){
        usuarioService = new UsuarioServiceImpl(usuarioRepository);
        usuario = Usuario.builder()
                .id(1L)
                .nome("anna")
                .email("anna@gmail.com")
                .senha("123")
                .build();
    }

    @Test
    @DisplayName("Verifica se o usuário foi salvo com sucesso")
    public void saveUser() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario usuarioSave = usuarioService.salvarUsuario(usuario);
        System.out.println(usuarioSave);
        assertThat(usuarioSave).isNotNull();
    }

    @Test
    @DisplayName("Verifica se o email ja existe no sistema")
    public void emailExists() {
        Assertions.assertFalse(usuarioService.validarEmail(usuario.getEmail()));
    }

    @Test
    @DisplayName("Precisa retornar se o usuário existe ou não")
    public void userNotFound() {
        usuarioService.salvarUsuario(usuario);
        Assertions.assertTrue(usuarioRepository.existsByEmail(usuario.getEmail()));
    }

    @Test
    @DisplayName("Precisa retornar que a senha inserida esta errada")
    public void validatePassword() {
        Assertions.assertEquals(usuario.getSenha(), "123456");
    }

}
