import { Link } from 'react-router-dom';
import logo from '../html-css-template/img/logo-red.png';


function EscolhaCadastro() {
    return (
        <>
            <div class="page dflex acenter jcenter">
                <div class="card bg-dark-red txt-center">
                    <a href="institucional.html">
                        <img class="logo transform" src={logo} />
                    </a>
                    <div class="buttons">
                        <Link to="/CadastroCliente" class="button big bg-white txt-black bg-hover-dark-red txt-hover-white">
                            Cadastrar-se como cliente
                        </Link>
                        <a href="" class="button big bg-white txt-black bg-hover-dark-red txt-hover-white">
                            Cadastrar-se como profissional
                        </a>
                    </div>
                    <div class="txt-white dflex acenter jcenter login">
                        Já possui cadastro? <a class="txt-white transform" href="login.html"><u><b>Entre aqui</b></u></a>
                    </div>
                </div>
            </div>
        </>
    );
}

export default EscolhaCadastro;