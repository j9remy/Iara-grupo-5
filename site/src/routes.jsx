import { BrowserRouter, Route, Routes } from "react-router-dom";
import EscolhaCadastro from "./pages/escolhaCadastro";

function Rotas(){
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/escolhaCadastro" element={<EscolhaCadastro/>}/>
            </Routes>
        </BrowserRouter>

    );
}

export default Rotas;