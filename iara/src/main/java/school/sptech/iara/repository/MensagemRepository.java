package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Mensagem;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
    List<Mensagem> findAllByChat_ServicoAtribuidoId(Integer id);
}
