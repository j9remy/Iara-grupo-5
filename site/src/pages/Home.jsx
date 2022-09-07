import { useState, useEffect } from "react";
import CarrosselCategorias from "../components/CarrosselCategorias";
import CarrosselProfissionais from "../components/CarrosselProfissionais";
import Footer from "../components/Footer";
import Header from "../components/Header";
import api from "../api";
import Slider from "react-slick";

function SampleNextArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{ ...style, display: "block" }}
      onClick={onClick}
    />
  );
}

function SamplePrevArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{ ...style, display: "absolute", color: "#de0235" }}
      onClick={onClick}
    />
  );
}

function Home() {
  const [profissionais, setProfissionais] = useState([]);

  const settings = {
    infinite: false,
    slidesToShow: 5,
    slidesToScroll: 1,
    nextArrow: <SampleNextArrow />,
    prevArrow: <SamplePrevArrow />,
  };


  useEffect(() =>{
    console.log("Entrou no useEffect");
    async function buscarCategoria(){
        const resposta = await api.get(`categoria/`);
        setProfissionais(resposta.data);
        console.log("OLHA O QUE VEIO DA API!!", resposta.data)
    }   
    buscarCategoria();
}, [])

  function selecionarCategoria(idCategoria) {
    //REQUISIÇÃO BACKEND

    if (idCategoria === 1) {

      setProfissionais([
        {
          nome: "Claudia Rita",
          distancia: "6,4 KM",
          habilidade: "Cacheado",
          avaliacao: "5",
          foto: "../img/profissionais/img-prof-Claudia.jpg",
        },
        {
          nome: "Paulo Henrique",
          distancia: "7,4 KM",
          habilidade: "Afro",
          avaliacao: "4",
          foto: "../img/profissionais/img-prof-Paulo.jpg",
        },
        {
          nome: "Letícia Maia",
          distancia: "6,4 KM",
          habilidade: "Cacheado",
          avaliacao: "4",
          foto: "../img/profissionais/img-prof-Leticia.jpg",
        },
        {
          nome: "Victor Assunção",
          distancia: "7,4 KM",
          habilidade: "Luzes",
          avaliacao: "4",
          foto: "../img/profissionais/img-prof-Victor.jpg",
        }
      ]);
    } else {
      setProfissionais([
        {
          nome: "Diogo",
          distancia: "7,4 KM",
          habilidade: "Liso",
          avaliacao: "4",
          foto: "../img/profissionais/img-prof-1.png",
        },
      ]);
    }

  }

  return (
    <>
      <Header />
      <main class="margin-top-thirty">
        <div class="container">
          <CarrosselCategorias funcaoCategoria={selecionarCategoria} />
          <div id="profissionais" class="profissionais">
            <Slider {...settings}>
              {profissionais.map((profissional) => (
                <CarrosselProfissionais
                  nome={profissional.nome}
                  habilidade={profissional.habilidade}
                  distancia={profissional.distancia}
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
  );
}
export default Home;