package br.alura.forum_hub.controller;

import br.alura.forum_hub.domain.usuario.DadosCadastroUsuario;
import br.alura.forum_hub.domain.usuario.Usuario;
import br.alura.forum_hub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroUsuario dados){

        var senhaCriptografada = passwordEncoder.encode(dados.senha());

        var usuario = new Usuario();
        usuario.setLogin(dados.login());
        usuario.setSenha(senhaCriptografada);

        repository.save(usuario);
    }
}