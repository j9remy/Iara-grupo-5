import CarrocelCategorias from "../components/CarrocelCategorias";
import CarrocelProfissionais from "../components/CarrocelProfissionais";
import Footer from "../components/Footer";
//import Header from "../components/Header";
import HeaderLogado from "../components/HearderLogado";
//import HeaderLogado from "../components/HearderLogado";


function Home() {
    const logado = false;
    return (
        <>
            <main class="margin-top-thirty">
                <div class="container">
                    <HeaderLogado />
                    <CarrocelCategorias />
                    <CarrocelProfissionais />
                    <Footer/>
                </div>
            </main>
        </>
    )

}
export default Home;