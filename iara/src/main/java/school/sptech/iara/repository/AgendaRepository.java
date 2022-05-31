package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Agenda;

import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
    Optional<Agenda> findByPrestador_Id(Integer id);
}
