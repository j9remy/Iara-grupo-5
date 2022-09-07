import api from "../api";
import { useState, useEffect } from "react";
import Slider from "react-slick";
import CarrosselProfissionais from "./CarrosselProfissionais";

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

function useSelecionarCategoria(idCategoria) {
  //REQUISIÇÃO BACKEND

  const [profissionais, setProfissionais] = useState([]);

  useEffect(() => {
    console.log("Entrou no useEffect");
    async function buscarCategoria(){
        const resposta = await api.get(`categoria/prestador/1`);
        setProfissionais(resposta.data);
        console.log("OLHA O QUE VEIO DA API!!", resposta.data)
    }   
    buscarCategoria();

}, [])

const settings = {
    infinite: false,
    slidesToShow: 5,
    slidesToScroll: 1,
    nextArrow: <SampleNextArrow />,
    prevArrow: <SamplePrevArrow />,
  };

return (

    <Slider {...settings}>
    {profissionais.map((profissional) => (
      <CarrosselProfissionais
        nome={profissional.nome}
        habilidade={profissional.habilidades[0].descricao}
        distancia={profissional.distancia}
        avaliacao={profissional.avaliacao}
        foto={profissional.foto}
      />
    ))}
  </Slider>

)

}

export default useSelecionarCategoria;