import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';



function HeaderColaborador() {
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
                <div class="prelative">
                    <button type="button" class="btn-no-style btn-profile dflex acenter jcenter">
                        <img src="../img/perfil-padrao.png" alt="Foto de perfil" />
                    </button>
                    <div class="itens jflex pabsolute txt-dark-red account-menu">
                        <a class="txt-dark-red transform-bold margin-bottom-10" href="">MINHA CONTA</a>
                        <a class="txt-dark-red transform-bold margin-bottom-10" href="" >ANÁLISES</a>
                        <a class="txt-dark-red transform-bold" href="/">SAIR</a>
                    </div>
                </div>
            </div>
        </header>
    )
}
export default HeaderColaborador;