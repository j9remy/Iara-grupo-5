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

function CarrocelProfissionais() {

    const settings = {
        infinite: true,
        slidesToShow: 5,
        slidesToScroll: 1,
        nextArrow: <SampleNextArrow />,
        prevArrow: <SamplePrevArrow />
    };
    return (
        <div id="profissionais" class="profissionais">
            <Slider {...settings}>
                <div class="item">
                    <div class="card bg-dark-red txt-white txt-center">
                        <img src="../img/profissionais/img-prof-1.png" />
                        <div class="nome">
                            <b>Ana Clara P</b><br />Cabeleireira
                        </div>
                        <div class="distancia">6,4 Km</div>
                        <div class="avaliacao" data-aval="4 estrelas">4 estrelas</div>
                        <a class="button bg-white txt-dark-red bg-hover-dark-red txt-hover-white" href=" ">AGENDAR</a>
                    </div>
                </div>
                <div class="item">
                    <div class="card bg-dark-red txt-white txt-center">
                        <img src="../img/profissionais/img-prof-2.png" />
                        <div class="nome">
                            <b>Salão Salone</b>
                        </div>
                        <div class="distancia">11,8 Km</div>
                        <div class="avaliacao" data-aval="3 estrelas">3 estrelas</div>
                        <a class="button bg-white txt-dark-red bg-hover-dark-red txt-hover-white" href=" ">AGENDAR</a>
                    </div>
                </div>
                <div class="item">
                    <div class="card bg-dark-red txt-white txt-center">
                        <img src="../img/profissionais/img-prof-3.png" />
                        <div class="nome">
                            <b>Estúdio Pink</b>
                        </div>
                        <div class="distancia">4,4 Km</div>
                        <div class="avaliacao" data-aval="5 estrelas">5 estrelas</div>
                        <a class="button bg-white txt-dark-red bg-hover-dark-red txt-hover-white" href=" ">AGENDAR</a>
                    </div>
                </div>
                <div class="item">
                    <div class="card bg-dark-red txt-white txt-center">
                        <img src="../img/profissionais/img-prof-4.png" />
                        <div class="nome">
                            <b>Arizona R</b><br />Cabeleireira
                        </div>
                        <div class="distancia">2,5 Km</div>
                        <div class="avaliacao" data-aval="4 estrelas">4 estrelas</div>
                        <a class="button bg-white txt-dark-red bg-hover-dark-red txt-hover-white" href=" ">AGENDAR</a>
                    </div>
                </div>
                <div class="item">
                    <div class="card bg-dark-red txt-white txt-center">
                        <img src="../img/profissionais/img-prof-5.png" />
                        <div class="nome">
                            <b>Diana Soares</b><br />Cabeleireira
                        </div>
                        <div class="distancia">8,3 Km</div>
                        <div class="avaliacao" data-aval="1 estrela">1 estrela</div>
                        <a class="button bg-white txt-dark-red bg-hover-dark-red txt-hover-white" href=" ">AGENDAR</a>
                    </div>
                </div>
                <div class="item">
                    <div class="card bg-dark-red txt-white txt-center">
                        <img src="../img/categorias/img-maquiagem.png" />
                        <div class="nome">
                            <b>Josefa</b><br />Cabeleireira
                        </div>
                        <div class="distancia">1,5 Km</div>
                        <div class="avaliacao" data-aval="0 estrelas">0 estrelas</div>
                        <a class="button bg-white txt-dark-red bg-hover-dark-red txt-hover-white" href=" ">AGENDAR</a>
                    </div>
                </div>

            </Slider>
        </div>
    );
}

export default CarrocelProfissionais;