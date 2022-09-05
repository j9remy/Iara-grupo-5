import { faPen } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

function CardAgendaDeAtendimentos() {
    return (
        <div class="card half prelative">
            <a class="btn-editar-perfil pabsolute bg-hover-white txt-hover-dark-red transform">
                <FontAwesomeIcon icon={faPen} />
            </a>
            <h3 class="txt-bigger txt-center txt-red txt-bold">Agenda de Atendimentos</h3>
            <div class="table red atendimentos">
                <table>
                    <tr>
                        <th>Serviço</th>
                        <th>Dia</th>
                        <th>Horário</th>
                        <th>Data</th>
                        <th>Cliente</th>
                    </tr>
                    <tr>
                        <td><span class="button bg-red txt-white margin-none pointer-none">Corte</span></td>
                        <td><span class="button bg-red txt-white margin-none pointer-none">Domingo</span></td>
                        <td><span class="button bg-red txt-white margin-none pointer-none">10:00</span></td>
                        <td><span class="button bg-red txt-white margin-none pointer-none">10/04/22</span></td>
                        <td><span class="button bg-red margin-none bg-hover-white"><u><a class="txt-white txt-hover-dark-red" href="">Isabela Santos</a></u></span></td>
                    </tr>
                </table>
            </div>
        </div>

    );
}

export default CardAgendaDeAtendimentos;