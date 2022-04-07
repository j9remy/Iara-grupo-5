package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
