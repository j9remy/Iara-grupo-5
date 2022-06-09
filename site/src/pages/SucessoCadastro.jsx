import { Link } from 'react-router-dom';
import logo from '../html-css-template/img/logo-red.png';

function SucessoCadastro() {
    return (
        <div class="page dflex acenter jcenter fdcolumn">
            <div class="card bg-dark-red txt-left">
                <Link to={'/'}>
                    <img class="logo transform logo-menor" src={logo} />
                </Link>
                <div class="dflex acenter jcenter txt-medium txt-white txt-bigger margin-top-thirty">
                    Cadastro realizado com sucesso!
                </div>
            </div>
            <button class="cta">
                <Link to="/login"><span class="hover-underline-animation"> Continuar </span></Link>
                <svg id="arrow-horizontal" xmlns="http://www.w3.org/2000/svg" width="30" height="10" viewBox="0 0 46 16">
                    <path id="Path_10" data-name="Path 10" d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z" transform="translate(30)"></path>
                </svg>
            </button>
        </div>
    );
}

export default SucessoCadastro;