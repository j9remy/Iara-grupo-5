import CarrosselCategorias from "../components/CarrosselCategorias";
import CarrosselProfissionais from "../components/CarrosselProfissionais";
import Footer from "../components/Footer";
import HeaderCliente from "../components/HearderCliente";

function Home() {
    return (
        <>
        
            <HeaderCliente />
            <main class="margin-top-thirty">
                <div class="container">
                    <CarrosselCategorias />
                    <CarrosselProfissionais />
                </div>
            </main>
            <Footer />
        </>
    )

}
export default Home;