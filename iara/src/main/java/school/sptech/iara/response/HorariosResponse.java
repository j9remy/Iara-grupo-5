package school.sptech.iara.response;

import java.time.LocalTime;

public class HorariosResponse {
    private LocalTime horaInicioMinima;
    private LocalTime horaInicioMaxima;

    public HorariosResponse(LocalTime horaInicioMinima, LocalTime horaInicioMaxima) {
        this.horaInicioMinima = horaInicioMinima;
        this.horaInicioMaxima = horaInicioMaxima;
    }

    public LocalTime getHoraInicioMinima() {
        return horaInicioMinima;
    }
    public LocalTime getHoraInicioMaxima() {
        return horaInicioMaxima;
    }
}
