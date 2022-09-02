import { useState, useEffect } from "react";
import CarrocelCategorias from "../components/CarrocelCategorias";
import CarrocelProfissionais from "../components/CarrocelProfissionais";
import Footer from "../components/Footer";
import Header from "../components/Header";

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


  function selecionarCategoria(idCategoria) {
    //REQUISIÇÃO BACKEND

    if (idCategoria === 1) {
        
        setProfissionais([
            {
              nome: "Ana clara",
              distancia: "6,4 KM",
              habilidade: "Cacheado",
              avaliacao: "4",
              foto: "../img/profissionais/img-prof-1.png",
            },
            {
                nome: "Diogo",
                distancia: "7,4 KM",
                habilidade: "Liso",
                avaliacao: "4",
                foto: "../img/profissionais/img-prof-1.png",
            },
            {
                nome: "Ana clara",
                distancia: "6,4 KM",
                habilidade: "Cacheado",
                avaliacao: "4",
                foto: "../img/profissionais/img-prof-1.png",
              },
              {
                  nome: "Diogo",
                  distancia: "7,4 KM",
                  habilidade: "Liso",
                  avaliacao: "4",
                  foto: "../img/profissionais/img-prof-1.png",
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
      <main class="margin-top-thirty">
        <div class="container">
          <Header />

          <CarrocelCategorias funcaoCategoria={selecionarCategoria} />

          <div id="profissionais" class="profissionais">
            <Slider {...settings}>
              {profissionais.map((profissional) => (
                <CarrocelProfissionais
                  nome={profissional.nome}
                  habilidade={profissional.habilidade}
                  distancia={profissional.distancia}
                  avaliacao={profissional.avaliacao}
                  foto={profissional.foto}
                />
              ))}
            </Slider>
          </div>

          <Footer />
        </div>
      </main>
    </>
  );
}
export default Home;