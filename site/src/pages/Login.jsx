import { Link } from 'react-router-dom';
import logo from '../html-css-template/img/logo-red.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faFacebookF, faGoogle } from '@fortawesome/free-brands-svg-icons';



function Login() {

    return (
        <>
            <div class="page dflex acenter jcenter">
                <div class="card bg-dark-red txt-left">
                    <a href="institucional.html">
                        <img class="logo transform" src={logo} />
                    </a>
                    <form id="login" class="campos dflex fdcolumn txt-medium">
                        <div class="user-input-wrp">
                            <input type="email" class="input" id="input-email" onkeyup="verificaConteudo" required />
                            <span class="floating-label">E-mail</span>
                        </div>
                        <div class="user-input-wrp">
                            <input type="password" class="input" id="input-senha" onkeyup="verificaConteudo" required />
                            <span class="floating-label">Senha</span>
                        </div>

                        <button class="button bg-white txt-black bg-hover-dark-red txt-hover-white" type="submit">ENTRAR</button>
                    </form>
                    <div class="dflex acenter jcenter">
                        <button class="button circle dflex acenter jcenter transform" aria-label="Login com Google" type="button">
                            <FontAwesomeIcon icon={faGoogle} />
                        </button>

                        <button class="button circle dflex acenter jcenter transform" aria-label="Login com facebook" type="button">
                            <FontAwesomeIcon icon={faFacebookF} />
                        </button>
                    </div>

                    <div class="txt-white dflex acenter jcenter login">
                        Ainda não possui cadastro? <Link class="txt-white transform" to="/escolhaCadastro"><u><b>Cadastre-se aqui</b></u></Link>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Login;