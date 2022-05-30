package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.model.ServicoAtribuido;

public interface ServicoAtribuidoRepository extends JpaRepository<ServicoAtribuido,Integer> {
}
