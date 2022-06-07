package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Portifolio;
import school.sptech.iara.model.Prestador;

import java.util.Optional;


public interface PortifolioRepository extends JpaRepository<Portifolio, Integer> {
    Optional<Portifolio> findByPrestador(Prestador prestador);
}
