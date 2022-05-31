package school.sptech.iara.repository;

import com.github.javafaker.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Agenda;
import school.sptech.iara.model.Semana;

import java.time.LocalDate;

public interface SemanaRepository extends JpaRepository<Semana, Integer> {
    Boolean existsByDiaInicialAndDiaFinal(LocalDate diaInicial, LocalDate diaFinal);
    Boolean existsByAgenda(Agenda agenda);
}
