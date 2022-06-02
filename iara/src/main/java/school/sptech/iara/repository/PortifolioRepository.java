package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Portifolio;
import school.sptech.iara.model.Prestador;


public interface PortifolioRepository extends JpaRepository<Portifolio, Integer> {
    Portifolio findByPrestador(Prestador prestador);
}
