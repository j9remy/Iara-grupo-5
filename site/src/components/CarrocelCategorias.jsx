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
                    <span class="item">
                        <img src="../img/categorias/img-corte.png" alt="Corte de cabelo" />
                        <span>Corte de Cabelo</span>
                    </span>
                </div>
                <div>
                    <span class="item">
                        <img src="../img/categorias/img-hidratacao.png" alt="Hidratação" />
                        <span>Hidratação</span>
                    </span>
                </div>
                <div>
                    <span class="item">
                        <img src="../img/categorias/img-maquiagem.png" alt="Maquiagem" />
                        <span>Maquiagem</span>
                    </span>
                </div>
                <div>
                    <span class="item">
                        <img src="../img/categorias/img-manicure.png" alt="Manicure" />
                        <span>Manicure</span>
                    </span>
                </div>
                <div>
                    <span class="item">
                        <img src="../img/categorias/img-designer-sobrancelhas.png" alt="Design de sobrancelhas" />
                        <span>Design de sobrancelhas</span>
                    </span>
                </div>
                <div>
                    <span class="item">
                        <img src="../img/categorias/img-massagem.png" alt="Massagem" />
                        <span>Massagem</span>
                    </span>
                </div>
                <div>
                    <span class="item">
                        <img src="../img/categorias/img-pedicure.png" alt="Pedicure" />
                        <span>Pedicure</span>
                    </span>
                </div>
            </Slider>
        </div>
    );
}

export default CarrocelCategorias;