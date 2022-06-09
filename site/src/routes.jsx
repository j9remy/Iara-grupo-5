import { BrowserRouter, Route, Routes } from "react-router-dom";
import EscolhaCadastro from "./pages/EscolhaCadastro";
import CadastroCliente from "./pages/CadastroCliente";
import Login from "./pages/Login";
import SucessoCadastro from "./pages/SucessoCadastro";
import Home from "./pages/Home";
import HomeCliente from "./pages/HomeCliente";

function Rotas(){
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/escolhaCadastro" element={<EscolhaCadastro/>}/>
                <Route path="/cadastroCliente" element={<CadastroCliente/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/sucessoCadastro" element={<SucessoCadastro/>}/>
                <Route path="/" element={<Home/>}/>
                <Route path="/home" element={<HomeCliente/>}/>
            </Routes>
        </BrowserRouter>

    );
}

export default Rotas;