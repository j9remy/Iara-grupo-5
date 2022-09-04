function CardTabelaDePrecos() {
    return (
        <>
            <div class="card half">
                <h3 class="txt-bigger txt-center txt-red txt-bold">Tabela de Preços</h3>
                <div class="dflex acenter jbetween margin-top-ten">
                    <b>Corte</b>
                    <input type="text" class="input-border-bottom-red txt-red" readonly value="(± 1 hora)" />
                    <span class="button small bg-red txt-white margin-none pointer-none">R$ 60,00</span>
                </div>
                <div class="dflex acenter jbetween margin-top-ten">
                    <b>Escova</b>
                    <input type="text" class="input-border-bottom-red txt-red" readonly value="(± 1 hora)" />
                    <span class="button small bg-red txt-white margin-none pointer-none">R$ 80,00</span>
                </div>
                <div class="dflex acenter jbetween margin-top-ten">
                    <b>Permanente</b>
                    <input type="text" class="input-border-bottom-red txt-red" readonly value="(± 1 hora)" />
                    <span class="button small bg-red txt-white margin-none pointer-none">R$ 80,00</span>
                </div>
                <div class="dflex acenter jbetween margin-top-ten">
                    <b>Tintura</b>
                    <input type="text" class="input-border-bottom-red txt-red" readonly value="(± 1 hora)" />
                    <span class="button small bg-red txt-white margin-none pointer-none">R$ 90,00</span>
                </div>
            </div>
        </>
    );

}

export default CardTabelaDePrecos;