package backend.psique.controller;

import backend.psique.model.paciente.*;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class PacienteController {
    @Autowired
    private PacienteRepository repository;
    @Autowired
    private ServicoPaciente servicoPaciente;

    @PostMapping("/cadPaciente")
    @Transactional
    @CrossOrigin(origins = {"http://127.0.0.1:80","http://127.0.0.1:5501","http://20.127.88.33","http://20.127.88.33:80","http://localhost:5501"})
    @PermitAll
    public ModelAndView cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
        ModelAndView mv = new ModelAndView("cadPaciente");
        mv.addObject("paciente", servicoPaciente.cadastrar(new Paciente(dados)));
        return mv;
    }

    @GetMapping("/listar")
    @CrossOrigin(origins = {"http://127.0.0.1:80","http://127.0.0.1:5501","http://20.127.88.33","http://20.127.88.33:80","http://localhost:5501"})
    public Page<Paciente> visualizar(Pageable paginacao){
        Page<Paciente> pacientes = repository.findAll(paginacao);
        return pacientes;
    }
    @GetMapping("/listarAll")
    @CrossOrigin(origins = {"http://127.0.0.1:80","http://127.0.0.1:5501","http://20.127.88.33","http://20.127.88.33:80","http://localhost:5501"})
    public List<DadosSelect> listarAll(){
        List<Paciente> pacientes = repository.findAll();
        List<DadosSelect> dados = pacientes.stream().map(DadosSelect::new).collect(Collectors.toList());
        return dados;
    }

}
