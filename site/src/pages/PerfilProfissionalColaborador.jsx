import { useEffect, useState } from "react";
import { useParams } from "react-router";
import api from "../api";
import CardAgendaDeAtendimentos from "../components/CardAgendaDeAtendimentos";
import CardInformacoesDoProfissional from "../components/CardInformacoesDoProfissional";
import CardPortifolio from "../components/CardPortifolio";
import CardTabelaDePrecos from "../components/CardTabelaDePrecos";
import Footer from "../components/Footer";
import HeaderColaborador from "../components/HearderColaborador";


function PerfilProfissionalColaborador() {


    const [infoPrestador, setPrestador] = useState([])
    const [preferencias, setPreferencias] = useState([])
    const idPrestador = useParams()
    console.log(idPrestador)

    useEffect(() => {
        const infoPrestador = localStorage.dadosUsuario;
        if (infoPrestador) {
            setPrestador(infoPrestador);
        }
        api.get(`/prestador/${idPrestador.id}`).then((res) => {
            setPrestador(res.data)
            setPreferencias(res.data.caracteristicas)
            console.log(localStorage)
        })
    },[])

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
                    <CardPortifolio />
                </div>
            </main>
            <Footer />
        </>
    )

}
export default PerfilProfissionalColaborador;