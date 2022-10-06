import Slider from "react-slick";
import { GoChevronRight } from "react-icons/go";
import { GoChevronLeft } from "react-icons/go";


function CarrosselCategorias(props) {
    const settings = {
        slidesToShow: 5,
        slidesToScroll: 1,
        nextArrow: <GoChevronRight color="white"/>,
        prevArrow: <GoChevronLeft color="white"/>
    };
    return (
        <div id="categorias" class="categorias card bg-red">
            <Slider {...settings}>
                <div>
                    <span class="item" onClick={() => props.funcaoCategoria(1)} >
                        <a href="#categorias">
                            <img src="../img/categorias/img-corte.png" alt="Corte de cabelo" />
                            <span>Corte de Cabelo</span>
                        </a>
                    </span>
                </div>
                <div>
                    <span class="item" onClick={() => props.funcaoCategoria(2)}>
                        <a href="#categorias">
                            <img src="../img/categorias/img-hidratacao.png" alt="Hidratação" />
                            <span>Hidratação</span>
                        </a>
                    </span>
                </div>
                <div>
                    <span class="item" onClick={() => props.funcaoCategoria(3)}>
                        <a href="#categorias">
                            <img src="../img/categorias/img-maquiagem.png" alt="Maquiagem" />
                            <span>Maquiagem</span>
                        </a>
                    </span>
                </div>
                <div>
                    <span class="item" onClick={() => props.funcaoCategoria(4)}>
                        <a href="#categorias">
                            <img src="../img/categorias/img-manicure.png" alt="Manicure" />
                            <span>Manicure</span>
                        </a>
                    </span>
                </div>
                <div>
                    <span class="item" onClick={() => props.funcaoCategoria(5)}>
                        <a href="#categorias">
                            <img src="../img/categorias/img-designer-sobrancelhas.png" alt="Design de sobrancelhas" />
                            <span>Design de sobrancelhas</span>
                        </a>
                    </span>
                </div>
                <div>
                    <span class="item" onClick={() => props.funcaoCategoria(6)}>
                        <a href="#categorias">
                            <img src="../img/categorias/img-massagem.png" alt="Massagem" />
                            <span>Massagem</span>
                        </a>
                    </span>
                </div>
                <div>
                    <span class="item" onClick={() => props.funcaoCategoria(7)}>
                        <a href="#categorias">
                        <img src="../img/categorias/img-pedicure.png" alt="Pedicure" />
                            <span>Pedicure</span>
                        </a>
                    </span>
                </div>
            </Slider>
        </div>
    );
}

export default CarrosselCategorias;
