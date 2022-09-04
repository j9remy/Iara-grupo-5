import CardAgendaDeAtendimentos from "../components/CardAgendaDeAtendimentos";
import CardInformacoesDoProfissional from "../components/CardInformacoesDoProfissional";
import CardPortifolio from "../components/CardPortifolio";
import CardTabelaDePrecos from "../components/CardTabelaDePrecos";
import Footer from "../components/Footer";
import HeaderColaborador from "../components/HearderColaborador";


function PerfilProfissionalColaborador() {
    return (
        <>
            <HeaderColaborador />
            <main class="margin-top-thirty">
                <div class="container">
                    <CardInformacoesDoProfissional />
                    <div class="dflex jbetween margin-top-thirty">
                        <CardTabelaDePrecos />
                        <CardAgendaDeAtendimentos />
                    </div>
                    <CardPortifolio/>
                </div>    
            </main>
            <Footer />
        </>
    )

}
export default PerfilProfissionalColaborador;