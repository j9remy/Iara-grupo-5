import { Link } from 'react-router-dom';
import logo from '../html-css-template/img/logo-red.png';


function EscolhaLogin() {
    return (
        <>
            <div class="page dflex acenter jcenter">
                <div class="card bg-dark-red txt-center">
                    <Link to={'/'}>
                        <img class="logo transform" src={logo} />
                    </Link>
                    <div class="buttons">
                        <Link to="/login" class="button big bg-white txt-black bg-hover-dark-red txt-hover-white">
                            Logar como cliente
                        </Link>
                        <Link to="/loginPrestador" class="button big bg-white txt-black bg-hover-dark-red txt-hover-white">
                            Logar como profissional
                        </Link>
                    </div>
                    <div class="txt-white dflex acenter jcenter login">
                        não possui cadastro? <Link class="txt-white transform" to="/escolhaCadastro"><u><b>Cadastrar aqui</b></u></Link>
                    </div>
                </div>
            </div>
        </>
    );

}

export default EscolhaLogin;