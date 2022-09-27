import CardInformacoesCliente from "../components/CardInformacoesCliente";
import Footer from "../components/Footer";
import HeaderCliente from "../components/HearderCliente";
import HeaderColaborador from "../components/HearderColaborador";

function PerfilCliente(){
    
    return(
        <>
            <HeaderColaborador />
            <main class="margin-top-thirty margin-bottom-thirty container">
                <CardInformacoesCliente />
            </main>
            <Footer/>
        </>
    )

}

export default PerfilCliente;