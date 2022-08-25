package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.iara.model.Servico;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    List<Servico> findAllByPrestador_Id(Integer id);
}
