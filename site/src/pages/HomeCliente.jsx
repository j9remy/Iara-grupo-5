import CarrocelCategorias from "../components/CarrocelCategorias";
import CarrocelProfissionais from "../components/CarrocelProfissionais";
import Footer from "../components/Footer";
import HeaderLogado from "../components/HearderLogado";


function Home() {
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