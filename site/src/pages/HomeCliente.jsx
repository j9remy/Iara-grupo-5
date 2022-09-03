import CarrosselCategorias from "../components/CarrosselCategorias";
import CarrosselProfissionais from "../components/CarrosselProfissionais";
import Footer from "../components/Footer";
import HeaderLogado from "../components/HearderLogado";


function Home() {
    return (
        <>
            <main class="margin-top-thirty">
                <div class="container">
                    <HeaderLogado />
                    <CarrosselCategorias />
                    <CarrosselProfissionais />
                    <Footer/>
                </div>
            </main>
        </>
    )

}
export default Home;