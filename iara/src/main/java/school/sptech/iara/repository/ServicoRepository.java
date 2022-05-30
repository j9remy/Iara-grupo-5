package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.iara.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
