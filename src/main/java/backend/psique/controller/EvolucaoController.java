package backend.psique.controller;

import backend.psique.model.evolucao.DadosCadastroEvolucao;
import backend.psique.model.evolucao.Evolucao;
import backend.psique.model.evolucao.EvolucaoRepository;
import backend.psique.model.evolucao.ServicoEvolucao;
import backend.psique.model.paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class EvolucaoController {
    @Autowired
   private EvolucaoRepository repository;
    @Autowired
    private ServicoEvolucao servicoEvolucao;

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroEvolucao dados ) {
        servicoEvolucao.cadastrar(new Evolucao(dados));
    }
    @GetMapping("/cadastrar/{cpf}")
        public String cadastrar(@PathVariable String cpf){
        return "Evolução cadastrada com sucesso "+cpf;
        }
    @GetMapping("/evolucoesListar")
    @CrossOrigin(origins = {"http://127.0.0.1:80","http://127.0.0.1:5501","http://20.127.88.33","http://20.127.88.33:80","http://localhost:5501"})
    public Page<Evolucao> visualizar(Pageable paginacao){
        Page<Evolucao> evolucaos = repository.findAll(paginacao);
        return evolucaos;
    }

    @GetMapping("/evolucoesListar/{cpf}")
    @CrossOrigin(origins = {"http://127.0.0.1:80","http://127.0.0.1:5501","http://20.127.88.33","http://20.127.88.33:80","http://localhost:5501"})
    public Page<Evolucao> listar(@PathVariable String cpf, Pageable pageable){
        Page<Evolucao> evolucaos = servicoEvolucao.findByCpf(cpf,pageable);
        return evolucaos;
    }

}
