import Slider from "react-slick";
import banner from "../html-css-template/img/banner/Banner-Iara - Copia.png";
import banner2 from "../html-css-template/img/banner/Banner-Iara2 - Copia.png";
import banner3 from "../html-css-template/img/banner/Banner-Iara3 - Copia.png";
import { GoChevronRight } from "react-icons/go";
import { GoChevronLeft } from "react-icons/go";

function CarrosselPropaganda() {
    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        nextArrow: <GoChevronRight color="rgb(222 2 53)" />,
        prevArrow: <GoChevronLeft  color="rgb(222 2 53)"/>
    };

    return (
        <div class="banner">
            <Slider {...settings}>
                <div class="img">
                    <img src={banner} class="radius"/>
                </div>
                <div class="img">
                    <img src={banner2} class="radius" />
                </div>
                <div class="img">
                    <img src={banner3} class="radius" />
                </div>
            </Slider>
        </div>
    );
}


export default CarrosselPropaganda;
