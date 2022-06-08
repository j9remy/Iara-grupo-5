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
            style={{ ...style, display: "block" }}
            onClick={onClick}
        />
    );
}

function CarrocelCategorias() {

    const settings = {
        infinite: true,
        slidesToShow: 5,
        slidesToScroll: 1,
        nextArrow: <SampleNextArrow />,
        prevArrow: <SamplePrevArrow />
    };
    return (
        <div id="categorias" class="categorias card bg-red">
            <Slider {...settings}>
                <div>
                    <a href="" class="item">
                        <img src="../img/categorias/img-corte.png" alt="Corte de cabelo" />
                        <span>Corte de Cabelo</span>
                    </a>
                </div>
                <div>
                    <a href="" class="item">
                        <img src="../img/categorias/img-hidratacao.png" alt="Hidratação" />
                        <span>Hidratação</span>
                    </a>
                </div>
                <div>
                    <a href="" class="item">
                        <img src="../img/categorias/img-maquiagem.png" alt="Maquiagem" />
                        <span>Maquiagem</span>
                    </a>
                </div>
                <div>
                    <a href="" class="item">
                        <img src="../img/categorias/img-manicure.png" alt="Manicure" />
                        <span>Manicure</span>
                    </a>
                </div>
                <div>
                    <a href="" class="item">
                        <img src="../img/categorias/img-designer-sobrancelhas.png" alt="Design de sobrancelhas" />
                        <span>Design de sobrancelhas</span>
                    </a>
                </div>
                <div>
                    <a href="" class="item">
                        <img src="../img/categorias/img-massagem.png" alt="Massagem" />
                        <span>Massagem</span>
                    </a>
                </div>
                <div>
                    <a href="" class="item">
                        <img src="../img/categorias/img-pedicure.png" alt="Pedicure" />
                        <span>Pedicure</span>
                    </a>
                </div>
            </Slider>
        </div>
    );
}

export default CarrocelCategorias;