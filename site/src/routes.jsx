import { BrowserRouter, Route, Routes } from "react-router-dom";
import EscolhaCadastro from "./pages/EscolhaCadastro";
import CadastroCliente from "./pages/CadastroCliente";
import Login from "./pages/Login";
import SucessoCadastro from "./pages/SucessoCadastro";
import Home from "./pages/Home";
import HomeCliente from "./pages/HomeCliente";
import CadastroProfissional from "./pages/CadastroProfissional";
import CadastroEndereco from "./pages/CadastroEndereco";
import CadastroHabilidades from "./pages/CadastroHabilidades";

function Rotas(){
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/escolhaCadastro" element={<EscolhaCadastro/>}/>
                <Route path="/cadastroCliente" element={<CadastroCliente/>}/>
                <Route path="/cadastroProfissional" element={<CadastroProfissional/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/sucessoCadastro" element={<SucessoCadastro/>}/>
                <Route path="/" element={<Home/>}/>
                <Route path="/home" element={<HomeCliente/>}/>
                <Route path="/cadastroEndereco" element={<CadastroEndereco/>}/>
                <Route path="/cadastroHabilidades" element={<CadastroHabilidades/>}/>
            </Routes>
        </BrowserRouter>

    );
}

export default Rotas;