import { Link } from 'react-router-dom';
import logo from '../html-css-template/img/logo-red.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFacebookF, faInstagram, faLinkedinIn, faTwitter, faYoutube } from '@fortawesome/free-brands-svg-icons';

function Footer() {
    return (
        <footer class="footer-logged">
            <div class="container dflex jbetween">
                <div>
                    <a class="logo transform">
                        <img alt="Logo" src="../img/logo.png" />
                    </a>
                </div>
                <div>
                    <h4>INSTITUCIONAL</h4>
                    <a href="institucional.html#como-funciona">Como Funciona</a>
                    <a href="institucional.html#sobre-nos">Sobre nós</a>
                </div>
                <div>
                    <h4>MINHA CONTA</h4>
                    <a href="">Meu Perfil</a>
                    <a href="">Meus Pontos</a>
                </div>
                <div>
                    <h4>CONTATO</h4>
                    <a href="">
                        Whatsapp<br />
                        (11) 99111-1111
                    </a>
                    <a href="mailto:contato@iara.com">
                        E-mail<br />
                        contato@iara.com
                    </a>
                </div>
                <div>
                    <h4>SIGA-NOS</h4>
                    <span>Acompanhe as nossas<br />
                        redes sociais</span>
                    <div class="dflex acenter">
                        <a href="" class="button circle txt-dark-red bg-white dflex acenter jcenter"><FontAwesomeIcon icon={faFacebookF} /></a>
                        <a href="" class="button circle txt-dark-red bg-white dflex acenter jcenter"><FontAwesomeIcon icon={faInstagram} /></a>
                        <a href="" class="button circle txt-dark-red bg-white dflex acenter jcenter"><FontAwesomeIcon icon={faTwitter} /></a>
                        <a href="" class="button circle txt-dark-red bg-white dflex acenter jcenter"><FontAwesomeIcon icon={faLinkedinIn} /></a>
                        <a href="" class="button circle txt-dark-red bg-white dflex acenter jcenter"><FontAwesomeIcon icon={faYoutube} /></a>
                    </div>
                </div>
            </div>
            <div class="txt-center margin-top-thirty txt-regular">© <b>IARA</b> - Todos os direitos reservados</div>
        </footer>
    )
}
export default Footer;