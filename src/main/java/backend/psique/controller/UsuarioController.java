package backend.psique.controller;

import backend.psique.model.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController

public class UsuarioController {
    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;
    @Autowired
    private UsuarioServico servico;



    public static String token;

    public UsuarioController(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/cadUser")
    @Transactional
    @CrossOrigin(origins = {"http://127.0.0.1:80","http://127.0.0.1:5501","http://20.127.88.33","http://20.127.88.33:80","http://localhost:5501"})
    public ResponseEntity<?> cadastrarUsuario(@RequestBody DadosCadastroUsuario user) {
        return servico.cadastrar(new Usuario(user));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/usuariosListar")
    @CrossOrigin(origins = {"http://127.0.0.1:80","http://127.0.0.1:5501","http://20.127.88.33","http://20.127.88.33:80","http://localhost:5501"})
    public Page<Usuario> listarUsuarios(Pageable paginacao) {
        Page<Usuario> usuarios = servico.findAll(paginacao);
        return usuarios;
    }
}