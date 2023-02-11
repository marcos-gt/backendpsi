package backend.psique.model.consulta;

import backend.psique.model.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ServicoConsulta {
    @Autowired
    private Mensagem mensagem;
    @Autowired
    private ConsultaRepository repository;

    public ResponseEntity<?>cadastrar(Consulta obj){
        if(obj.getValor() == 0){
            mensagem.setMensagem("Valor não pode ser 0");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(obj.getValor() < 0) {
            mensagem.setMensagem("Valor não pode ser negativo");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else
            repository.save(obj);
            mensagem.setMensagem("Consulta cadastrada com sucesso");
            return new ResponseEntity<>(mensagem,HttpStatus.CREATED);
    }


    public List<Consulta> findByData_consulta(Date data) {
        return repository.findByDataConsulta(data);
    }
}