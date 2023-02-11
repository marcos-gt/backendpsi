package backend.psique.controller;

import backend.psique.model.usuario.DadosCadastroForm;
import backend.psique.model.usuario.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class CadastrarUsuarioController {
    @Autowired
    private UsuarioServico servico;
    @GetMapping("/cadastrarUsuario")
    public ModelAndView cadastrarUsuario(){
        ModelAndView mv = new ModelAndView("cadastrarUsuario");
        return mv;
    }
    @PostMapping("/cadastrarUsuario")
    @CrossOrigin(origins = {"http://127.0.0.1:80","http://127.0.0.1:5501","http://20.127.88.33","http://20.127.88.33:80","http://localhost:5501"})
    public ResponseEntity<?> cadastrarUsuarioPost(@RequestBody DadosCadastroForm obj, BindingResult result, RedirectAttributes attributes){
        System.out.println(obj);
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        return servico.cadastrarForm(obj);
    }
}
