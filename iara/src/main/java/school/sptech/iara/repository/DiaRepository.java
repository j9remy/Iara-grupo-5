package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Dia;
import school.sptech.iara.model.Agendamento;

import java.util.Optional;

public interface DiaRepository extends JpaRepository<Dia, Integer> {
}
