function CardAgendarAtendimentos() {

    return (
        <div class="card half prelative">
            <div id="mensagem_agendamento" class="pabsolute dflex acenter jcenter mensagem"> {/*}classe show para mostrar mensagem*/}
                    <div class="txt-bold txt-bigger margin-bottom-thirty txt-dark-red" id="titulo_mensagem_agendamento">Agendado com Sucesso!</div>
                    <div class="margin-bottom-10 txt-medium">Serviço: <span id="servico_mensagem_agendamento"></span></div>
                    <div class="margin-bottom-10 txt-medium">Data: <span id="data_mensagem_agendamento"></span></div>
                    <div class="txt-medium">Horário: <span id="horario_mensagem_agendamento"></span></div>
                </div>
            <h3 class="txt-bigger txt-center txt-red txt-bold">Agendar Atendimentos</h3>
            <form>
                <div>
                    <h4 class="margin-bottom-10">Selecione o Serviço:</h4>
                    <div class="dflex fwrap">
                        <div class="prelative margin-right-twenty margin-bottom-10">
                            <input required type="radio" id="selecione_servico_corte" name="selecione_servico" value="Corte" class="radio-button" />
                            <label for="selecione_servico_corte" class="button small bg-white txt-dark-red bg-hover-red box-shadow-none-hover txt-hover-white margin-none">Corte</label>
                        </div>
                        <div class="prelative margin-right-twenty margin-bottom-10">
                            <input required type="radio" id="selecione_servico_escova" name="selecione_servico" value="Escova" class="radio-button" />
                            <label for="selecione_servico_escova" class="button small bg-white txt-dark-red bg-hover-red box-shadow-none-hover txt-hover-white margin-none">Escova</label>
                        </div>
                        <div class="prelative margin-right-twenty margin-bottom-10">
                            <input required type="radio" id="selecione_servico_tintura" name="selecione_servico" value="Tintura" class="radio-button" />
                            <label for="selecione_servico_tintura" class="button small bg-white txt-dark-red bg-hover-red box-shadow-none-hover txt-hover-white margin-none">Tintura</label>
                        </div>
                    </div>
                </div>
                <div>
                    <h4 class="margin-bottom-10">Selecione o Dia:</h4>
                    <input required class="input max-content" type="date"/>
                </div>
                <div>
                    <h4 class="margin-bottom-10">Selecione o Horário:</h4>
                    <div class="dflex fwrap">
                        <div class="prelative margin-right-twenty margin-bottom-10">
                            <input required type="radio" id="selecione_horario_0900" name="selecione_horario" value="09:00" class="radio-button"/>
                                <label for="selecione_horario_0900" class="button small bg-white txt-dark-red bg-hover-red box-shadow-none-hover txt-hover-white margin-none">09:00</label>
                        </div>
                        <div class="prelative margin-right-twenty margin-bottom-10">
                            <input required type="radio" id="selecione_horario_1030" name="selecione_horario" value="10:30" class="radio-button"/>
                                <label for="selecione_horario_1030" class="button small bg-white txt-dark-red bg-hover-red box-shadow-none-hover txt-hover-white margin-none">10:30</label>
                        </div>
                        <div class="prelative margin-right-twenty margin-bottom-10">
                            <input required type="radio" id="selecione_horario_1300" name="selecione_horario" value="13:00" class="radio-button"/>
                                <label for="selecione_horario_1300" class="button small bg-white txt-dark-red bg-hover-red box-shadow-none-hover txt-hover-white margin-none">13:00</label>
                        </div>
                        <div class="prelative margin-right-twenty margin-bottom-10">
                            <input required type="radio" id="selecione_horario_1430" name="selecione_horario" value="14:30" class="radio-button"/>
                                <label for="selecione_horario_1430" class="button small bg-white txt-dark-red bg-hover-red box-shadow-none-hover txt-hover-white margin-none">14:30</label>
                        </div>
                        <div class="prelative margin-right-twenty margin-bottom-10">
                            <input required type="radio" id="selecione_horario_1600" name="selecione_horario" value="16:00" class="radio-button"/>
                                <label for="selecione_horario_1600" class="button small bg-white txt-dark-red bg-hover-red box-shadow-none-hover txt-hover-white margin-none">16:00</label>
                        </div>
                        <div class="prelative margin-right-twenty margin-bottom-10">
                            <input required type="radio" id="selecione_horario_1730" name="selecione_horario" value="17:30" class="radio-button"/>
                                <label for="selecione_horario_1730" class="button small bg-white txt-dark-red bg-hover-red box-shadow-none-hover txt-hover-white margin-none">17:30</label>
                        </div>
                        <div class="prelative margin-right-twenty margin-bottom-10">
                            <input disabled required type="radio" id="selecione_horario_1900" name="selecione_horario" value="19:00" class="radio-button"/>
                                <label for="selecione_horario_1900" class="button small bg-white txt-dark-red bg-hover-red box-shadow-none-hover txt-hover-white margin-none">19:00</label>
                        </div>
                        <div class="prelative margin-right-twenty margin-bottom-10">
                            <input disabled required type="radio" id="selecione_horario_2030" name="selecione_horario" value="20:30" class="radio-button"/>
                                <label for="selecione_horario_2030" class="button small bg-white txt-dark-red bg-hover-red box-shadow-none-hover txt-hover-white margin-none">20:30</label>
                        </div>
                    </div>
                </div>
                <button class="button bg-red txt-white bg-hover-white txt-hover-dark-red margin-auto margin-top-thirty" type="submit">AGENDAR</button>
            </form>
        </div>
    );

}

export default CardAgendarAtendimentos;
