import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { Link } from 'react-router-dom';

function Header() {
    return (
        <header class="header-logged">
            <div class="container dflex acenter jbetween">
                <a href=" " class="logo transform">
                    <img alt="Logo" src="../img/logo.png" />
                </a>
                <form>
                    <input type="text" placeholder="Buscar" class="input-search" required />
                    <button type="submit" class="btn-search">
                        <FontAwesomeIcon icon={faMagnifyingGlass} />
                    </button>
                </form>
                <div class="dflex acenter">
                    <a href=" " class="txt-dark-red txt-medium margin-right-fifty transform">INSTITUCIONAL</a>
                </div>
                <Link to={'/escolhacadastro'}><button class="button bg-red txt-white bg-hover-white txt-hover-dark-red box-shadow-none-hover" >CADASTRE-SE</button></Link>
                <Link to={'/escolhaLogin'}><button class="button bg-white txt-black bg-hover-dark-red txt-hover-white box-shadow-none-hover" >LOGIN</button></Link>
            </div>
        </header>
    )
}
export default Header;
