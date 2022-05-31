import { BrowserRouter, Route, Routes } from "react-router-dom";
import EscolhaCadastro from "./pages/EscolhaCadastro";
import CadastroCliente from "./pages/CadastroCliente";

function Rotas(){
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/escolha-cadastro" element={<EscolhaCadastro/>}/>
                <Route path="/cadastro-cliente" element={<CadastroCliente/>}/>
            </Routes>
        </BrowserRouter>

    );
}

export default Rotas;