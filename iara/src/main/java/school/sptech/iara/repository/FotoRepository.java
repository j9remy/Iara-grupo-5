package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Foto;
import school.sptech.iara.model.Portifolio;

import java.util.List;
import java.util.Optional;

public interface FotoRepository extends JpaRepository<Foto,Integer> {
    List<Foto> findAllByPortifolioOrderByDataAsc(Portifolio portifolio);
    Optional<Foto> findByPortifolioAndId(Portifolio portifolio, Integer id);
}
