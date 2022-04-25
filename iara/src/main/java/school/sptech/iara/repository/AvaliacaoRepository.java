package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.AvaliacaoCliente;

public interface AvaliacaoRepository extends JpaRepository<AvaliacaoCliente,Integer> {

}