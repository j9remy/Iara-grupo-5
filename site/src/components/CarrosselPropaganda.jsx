import Slider from "react-slick";
import banner from "../html-css-template/img/banner/Banner-Iara.png";
import banner2 from "../html-css-template/img/banner/Banner-Iara2.png";
import banner3 from "../html-css-template/img/banner/Banner-Iara3.png";
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
        nextArrow: <GoChevronRight color="white" />,
        prevArrow: <GoChevronLeft  color="white"/>
    };

    return (
        <div class="categorias item">
            <Slider {...settings}>
                <div class="img">
                    <img src={banner} />
                </div>
                <div class="img">
                    <img src={banner2} />
                </div>
                <div class="img">
                    <img src={banner3} />
                </div>
            </Slider>
        </div>
    );
}


export default CarrosselPropaganda;