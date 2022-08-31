package school.sptech.iara.response;

import java.time.LocalDate;
import java.util.List;

public class HorariosDisponiveisResponse {
    private LocalDate data;
    private List<HorariosResponse> horarios;

    public HorariosDisponiveisResponse(LocalDate data, List<HorariosResponse> horarios) {
        this.data = data;
        this.horarios = horarios;
    }

    public LocalDate getData() {
        return data;
    }
    public List<HorariosResponse> getHorarios() {
        return horarios;
    }
}
