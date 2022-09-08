import Slider from "react-slick";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPen } from '@fortawesome/free-solid-svg-icons';
import { GoChevronRight } from "react-icons/go";
import { GoChevronLeft } from "react-icons/go";
import imagem1 from "../html-css-template/img/portfolio/1.png";
import imagem2 from "../html-css-template/img/portfolio/2.png";
import imagem3 from "../html-css-template/img/portfolio/3.png";
import imagem4 from "../html-css-template/img/portfolio/4.png";



function CardPortifolio() {
    
    const settings = {
        infinite: true,
        slidesToShow: 3,
        slidesToScroll: 1,
        arrows: true,
        dots: true,
        nextArrow: <GoChevronRight color="red" />,
        prevArrow: <GoChevronLeft  color="red"/>
    };

    return (
        <div class="card margin-top-thirty prelative">
            <a class="btn-editar-perfil pabsolute bg-hover-white txt-hover-dark-red transform">
                <FontAwesomeIcon icon={faPen} />
            </a>
            <h3 class="txt-bigger txt-red txt-bold">Portfólio</h3>
            <div id="portfolio" class="padding-zero-twenty">
                <Slider {...settings}>
                    <div class="padding-zero-twenty">
                        <img src={imagem1} alt="Imagem 1 do Portfólio" />
                    </div>
                    <div class="padding-zero-twenty">
                        <img src={imagem2} alt="Imagem 2 do Portfólio" />
                    </div>
                    <div class="padding-zero-twenty">
                        <img src={imagem3} alt="Imagem 3 do Portfólio" />
                    </div>
                    <div class="padding-zero-twenty">
                        <img src={imagem4} alt="Imagem 4 do Portfólio" />
                    </div>
                </Slider>
            </div>
        </div>
    );
}

export default CardPortifolio;