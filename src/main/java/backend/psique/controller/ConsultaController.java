package backend.psique.controller;

import backend.psique.model.consulta.Consulta;
import backend.psique.model.consulta.ConsultaRepository;
import backend.psique.model.consulta.DadosConsulta;
import backend.psique.model.consulta.ServicoConsulta;
import backend.psique.model.psicologo.Psicologo;
import backend.psique.model.psicologo.PsicologoServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping
public class ConsultaController {
    @Autowired
    private ConsultaRepository repository;
    @Autowired
    private ServicoConsulta servicoConsulta;
    @Autowired
    private PsicologoServico servicoPsicologo;
    @PostMapping("cadastrarconsulta")
    @Transactional
    @CrossOrigin(origins = {"http://127.0.0.1:80", "http://20.127.88.33","http://127.0.0.1:5501"})
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosConsulta dados){
        return servicoConsulta.cadastrar(new Consulta(dados));
    }
    @GetMapping("/consultasListar")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("consultasListar");
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        List<Consulta> consultas = repository.findAll();
        List<String> datas = new ArrayList<>();
        for(Consulta c : consultas){
            String date = simpleDateFormat.format(c.getData_consulta());
            System.out.println(date);
            datas.add(date);
        }
        mv.addObject("consultas",consultas);
        mv.addObject("datas",datas);
        return mv;
    }
    @GetMapping("/cadConsulta")
    public ModelAndView cadastro(){
        ModelAndView mv = new ModelAndView("cadConsulta");
        List<Psicologo> psicologos = servicoPsicologo.listarTodos();
        mv.addObject("psicologos", psicologos);
        return mv;
    }
    @GetMapping("/listar/{cpf}")
    public List<Consulta> ListarPorCpf(@PathVariable String cpf){
        return repository.findByPaciente_Cpf(cpf);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = {"http://127.0.0.1:80","http://127.0.0.1:5501","http://20.127.88.33","http://20.127.88.33:80","http://localhost:5501"})
    @GetMapping("/listarConsulta/{data}")
    public List<Consulta> listarPorData(@PathVariable String data) throws ParseException {
        Date data_consulta = new SimpleDateFormat("yyyy-MM-dd").parse(data);
        return servicoConsulta.findByData_consulta(data_consulta);
    }
    @GetMapping("/listarConsulta")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = {"http://127.0.0.1:80","http://127.0.0.1:5501","http://20.127.88.33","http://20.127.88.33:80","http://localhost:5501"})
    public Page<Consulta> Listar(Pageable paginacao){
        Page<Consulta> consultas = repository.findAll(paginacao);
        return consultas;
    }
}
