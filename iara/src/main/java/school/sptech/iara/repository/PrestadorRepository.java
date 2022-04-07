package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Prestador;

public interface PrestadorRepository extends JpaRepository<Prestador, Integer> {
}
