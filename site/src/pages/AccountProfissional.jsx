import { useEffect, useState } from "react";
import { useParams } from "react-router";
import api from "../api";
import CardAgendaDeAtendimentos from "../components/CardAgendaDeAtendimentos";
import CardInformacoesDoProfissionalAdm from "../components/CardInformacoesDoProfissionalAdm"
import CardPortifolioAdm from "../components/CardPortifolioAdm";
import CardTabelaDePrecosAdm from "../components/CardTabelaDePrecosAdm";
import Footer from "../components/Footer";
import HeaderColaborador from "../components/HearderColaborador";


function AccountProfissional() {


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
                    <CardInformacoesDoProfissionalAdm />
                    <div class="dflex jbetween margin-top-thirty">
                        <CardTabelaDePrecosAdm />
                        <CardAgendaDeAtendimentos />
                    </div>
                    <CardPortifolioAdm />
                </div>
            </main>
            <Footer />
        </>
    )

}
export default AccountProfissional;