import CardAgendaCliente from "../components/CardAgendaCliente";
import Footer from "../components/Footer";
import HeaderCliente from "../components/HearderCliente";
import CardInformacoesClienteAdm from "./CardInformacoesClienteAdm";

function AccountCliente() {

    return (
        <>
            <HeaderCliente />
            <main class="margin-top-thirty margin-bottom-thirty container">
                <CardInformacoesClienteAdm />
                <div class="dflex jbetween margin-top-thirty">
                    <CardAgendaCliente/>
                </div>
            </main>
            <Footer/>
        </>
    );
}

export default AccountCliente;