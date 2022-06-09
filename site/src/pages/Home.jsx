import CarrocelCategorias from "../components/CarrocelCategorias";
import CarrocelProfissionais from "../components/CarrocelProfissionais";
import Footer from "../components/Footer";
import Header from "../components/Header";


function Home() {
    return (
        <>
            <main class="margin-top-thirty">
                <div class="container">
                    <Header />
                    <CarrocelCategorias />
                    <CarrocelProfissionais />
                    <Footer/>
                </div>
            </main>
        </>
    )

}
export default Home;