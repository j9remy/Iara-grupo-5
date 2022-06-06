package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.AvaliacaoCliente;
import school.sptech.iara.model.Cliente;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<AvaliacaoCliente,Integer> {
    List<AvaliacaoCliente> findAllByCliente(Cliente cliente);
}