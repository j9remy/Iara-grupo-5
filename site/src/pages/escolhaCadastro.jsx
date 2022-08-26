import { Link } from 'react-router-dom';
import logo from '../html-css-template/img/logo-red.png';

function EscolhaCadastro() {
    return (
        <>
            <div class="page dflex acenter jcenter">
                <div class="card bg-dark-red txt-center">
                    <Link to={'/home'}>
                        <img class="logo transform" src={logo} />
                    </Link>
                    <div class="buttons">
                        <Link to="/cadastrocliente" class="button big bg-white txt-black bg-hover-dark-red txt-hover-white">
                            Cadastrar-se como cliente
                        </Link>
                        <Link to="/cadastroProfissional" class="button big bg-white txt-black bg-hover-dark-red txt-hover-white">
                            Cadastrar-se como profissional
                        </Link>
                    </div>
                    <div class="txt-white dflex acenter jcenter login">
                        Já possui cadastro? <Link class="txt-white transform" to="/login"><u><b>Entre aqui</b></u></Link>
                    </div>
                </div>
            </div>
        </>
    );
}

export default EscolhaCadastro;