import CarrosselCategorias from "../components/CarrosselCategorias";
import CarrosselProfissionais from "../components/CarrosselProfissionais";
import Footer from "../components/Footer";
import HeaderCliente from "../components/HearderCliente";
import { useState, useEffect } from "react";
import { GoChevronRight } from "react-icons/go";
import { GoChevronLeft } from "react-icons/go";
import api from "../api";
import CarrosselPropaganda from "../components/CarrosselPropaganda";
import Slider from "react-slick";

function Home() {
    const [profissionais, setProfissionais] = useState([]);

    const settings = {
        slidesToShow: 5,
        slidesToScroll: 1,
        nextArrow: <GoChevronRight color="white" />,
        prevArrow: <GoChevronLeft color="white" />
    };

    async function selecionarCategoria(idCategoria) {
        const resposta = await api.get(`categoria/prestador/${idCategoria}`);
        setProfissionais(resposta.data);
        console.log("OLHA O QUE VEIO DA API!!", resposta.data)
    }
    return (
        <>

            <HeaderCliente />
            <main class="margin-top-thirty">
                <div class="container">
                    <CarrosselPropaganda />
                    <CarrosselCategorias funcaoCategoria={selecionarCategoria} />
                    <div id="profissionais" class="profissionais">
                        <Slider {...settings}>
                            {profissionais.map((profissional) => (
                                <CarrosselProfissionais
                                    nome={profissional.nome}
                                    habilidade={profissional.habilidades[0].descricao}
                                    distancia={profissional.distancia + " KM"}
                                    avaliacao={profissional.avaliacao}
                                    foto={profissional.foto}
                                />
                            ))}
                        </Slider>
                    </div>
                </div>
            </main>
            <Footer />
        </>
    )

}
export default Home;