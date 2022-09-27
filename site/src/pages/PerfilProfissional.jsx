import CardAgendarAtendimentos from "../components/CardAgendarAtendimento";
import CardInformacoesDoProfissional from "../components/CardInformacoesDoProfissional";
import CardPortifolio from "../components/CardPortifolio";
import CardTabelaDePrecos from "../components/CardTabelaDePrecos";
import Footer from "../components/Footer";
import HeaderCliente from "../components/HearderCliente";

function PerfilProfissional() {

    return (
        <>
            <HeaderCliente />
            <main class="margin-top-thirty">
                <div class="container">
                    <CardInformacoesDoProfissional />
                    <div class="dflex jbetween margin-top-thirty">
                        <CardTabelaDePrecos/>
                        <CardAgendarAtendimentos/>
                    </div>
                    <CardPortifolio/>
                </div>
            </main>
            <Footer/>
        </>
    );

}

export default PerfilProfissional;