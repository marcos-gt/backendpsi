package backend.psique.controller;

import backend.psique.model.usuario.DadosAutenticacao;
import backend.psique.model.usuario.Usuario;
import backend.psique.model.usuario.UsuarioServico;
import backend.psique.security.DadosTokenJWT;
import backend.psique.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class IndexController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioServico servico;
    @Autowired
    private TokenService tokenService;

    @PreAuthorize("hasAnyRole('ROLE_USER,ROLE_ADMIN')")
    @GetMapping("/index")
    @CrossOrigin(origins = {"http://127.0.0.1:80","http://127.0.0.1:5501","http://20.127.88.33","http://20.127.88.33:80","http://localhost:5501"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    @PostMapping("/login")
    @CrossOrigin(origins = {"http://127.0.0.1:80","http://127.0.0.1:5501","http://20.127.88.33","http://20.127.88.33:80","http://localhost:5501"})
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.username(), dados.password());
        var auth = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
