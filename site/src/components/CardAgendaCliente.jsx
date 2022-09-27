function CardAgendaCliente() {

    return (
        <div class="card prelative width-100-porc">
            <h3 class="txt-bigger txt-center txt-red txt-bold">Agenda de Atendimentos</h3>
            <div class="table red atendimentos">
                <table>
                    <tr>
                        <th>Serviço</th>
                        <th>Dia</th>
                        <th>Horário</th>
                        <th>Data</th>
                        <th>Profissional</th>
                    </tr>
                    <tr>
                        <td><span class="button bg-red txt-white margin-none pointer-none">Corte</span></td>
                        <td><span class="button bg-red txt-white margin-none pointer-none">Domingo</span></td>
                        <td><span class="button bg-red txt-white margin-none pointer-none">10:00</span></td>
                        <td><span class="button bg-red txt-white margin-none pointer-none">10/04/22</span></td>
                        <td><span class="button bg-red margin-none bg-hover-white link-profissional"><u><a class="txt-white" href="">Ana Clara Pinheiros</a></u></span></td>
                    </tr>
                </table>
            </div>
        </div>
    )

}

export default CardAgendaCliente;