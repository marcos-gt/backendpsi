package backend.psique.controller;

import backend.psique.model.psicologo.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class PsiController {
    @Autowired
    private PsicologoRepository repository;
    @Autowired
    private PsicologoServico servicoPsicologo;

    @PostMapping("/cadastroPsi")
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPsicologo dados){
        servicoPsicologo.cadastro(new Psicologo(dados));
    }
    @GetMapping("/viewsPsi")
    @CrossOrigin(origins = {"http://127.0.0.1:80","http://127.0.0.1:5501","http://20.127.88.33","http://20.127.88.33:80","http://localhost:5501"})
    public Page<Psicologo> visualizar(Pageable paginacao){
        Page<Psicologo> psicologos = servicoPsicologo.findAll(paginacao);
        return psicologos;
    }
    @GetMapping("/DadosSelectPsicologo")
    @CrossOrigin(origins = {"http://127.0.0.1:80","http://127.0.0.1:5501","http://20.127.88.33","http://20.127.88.33:80","http://localhost:5501"})
    public List<DadosSelectPsicologo> listarAll(){
        List<Psicologo> psicologos = repository.findAll();
        List<DadosSelectPsicologo> dados = psicologos.stream().map(DadosSelectPsicologo::new).collect(Collectors.toList());
        return dados;
    }
}
