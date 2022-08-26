package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Agenda;
import school.sptech.iara.model.Agendamento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    List<Agendamento> findAllByAgendaAndData(Agenda agenda, LocalDate data);
    List<Agendamento> findAllByAgendaAndDataOrderByHoraInicio(Agenda agenda, LocalDate data);
    List<Agendamento> findAllByAgendaOrderByDataAscHoraInicioAsc(Agenda agenda);
    List<Agendamento> findAllByAgendaAndDataAndHoraInicioAndHoraFim(Agenda agenda, LocalDate data, LocalTime hrIn, LocalTime hrFim);
    List<Agendamento> findAllByDataBefore(LocalDate day);
    List<Agendamento> findAllByAgendaAndDataAfter(Agenda agenda, LocalDate day);
    Integer countAgendamentoByData(LocalDate data);
    Agendamento findByServicoAtribuido_Id(Integer idServicoAttr);
}
