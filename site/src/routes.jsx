import { BrowserRouter, Route, Routes } from "react-router-dom";
import EscolhaCadastro from "./pages/EscolhaCadastro";
import CadastroCliente from "./pages/CadastroCliente";

function Rotas(){
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/escolhaCadastro" element={<EscolhaCadastro/>}/>
                <Route path="/cadastroCliente" element={<CadastroCliente/>}/>
            </Routes>
        </BrowserRouter>

    );
}

export default Rotas;