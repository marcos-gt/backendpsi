package backend.psique.model.consulta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, String> {
    List<Consulta> findByPaciente_Cpf(String cpf);


    @Query("SELECT c FROM consulta c WHERE c.data_consulta = :data")
    List<Consulta> findByDataConsulta(Date data);
}
