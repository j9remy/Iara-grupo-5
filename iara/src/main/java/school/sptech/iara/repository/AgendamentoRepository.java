package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Agenda;
import school.sptech.iara.model.Agendamento;

import java.time.LocalDate;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    List<Agendamento> findAllByAgendaAndData(Agenda agenda, LocalDate data);
}
