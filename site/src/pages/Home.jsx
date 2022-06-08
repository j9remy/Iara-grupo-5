import CarrocelCategorias from "../components/CarrocelCategorias";
import CarrocelProfissionais from "../components/CarrocelProfissionais";
import Footer from "../components/Footer";

function Home() {
    return (
        <>
            <main class="margin-top-thirty">
                <div class="container">
                    <CarrocelCategorias />
                    <CarrocelProfissionais />
                    <Footer/>
                </div>
            </main>
        </>
    )

}
export default Home;