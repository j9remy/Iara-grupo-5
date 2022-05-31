import { BrowserRouter, Route, Routes } from "react-router-dom";
import EscolhaCadastro from "./pages/EscolhaCadastro";
import CadastroCliente from "./pages/CadastroCliente";
import Login from "./pages/Login";

function Rotas(){
    return(
        <BrowserRouter>
            <Routes>
<<<<<<< HEAD
                <Route path="/escolhaCadastro" element={<EscolhaCadastro/>}/>
                <Route path="/cadastroCliente" element={<CadastroCliente/>}/>
                <Route path="/login" element={<Login/>}/>
=======
                <Route path="/escolha-cadastro" element={<EscolhaCadastro/>}/>
                <Route path="/cadastro-cliente" element={<CadastroCliente/>}/>
>>>>>>> 337139a416ce724edd27fa76e468d4f1e4abf6b3
            </Routes>
        </BrowserRouter>

    );
}

export default Rotas;