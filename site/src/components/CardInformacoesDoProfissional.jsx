import { faPen } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';

function CardInformacoesDoProfissional() {

    return (
        <div class="card prelative">
            <Link to={"/cadastroProfissional"}>
                <a class="btn-editar-perfil pabsolute bg-hover-white txt-hover-dark-red transform">
                    <FontAwesomeIcon icon={faPen} />
                </a>
            </Link>

            <div class="dflex acenter jbetween">
                <div class="dflex acenter jbetween">
                    <img src="../img/foto-perfil.png" alt="Foto de perfil" class="margin-right-twenty" />
                    <div>
                        <b>Ana Clara Pinheiros, 32 anos</b><br />Cabeleireira
                    </div>
                </div>
                <div class="dflex acenter jbetween">
                    <a href="#" class="button bg-red txt-white bg-hover-white txt-hover-dark-red margin-none margin-right-twenty">ANÁLISES</a>
                    <div class="button bg-red txt-white bg-hover-white txt-hover-dark-red margin-none dflex acenter jbetween"><span>4</span>/5 <i class="icon star"></i></div>
                </div>
            </div>
            <div class="margin-top-thirty dflex jbetween">
                <div>
                    <b>Telefone</b><br />
                    <span>(11) 99724-0127</span><br /><br />
                    <b>Gênero</b><br />
                    <span>Feminino</span>
                </div>
                <div>
                    <b>Endereço</b><br />
                    <span>Rua Francisco de Oliveira, 144<br />
                        Parque das Árvores, São Paulo<br />
                        SP - 14404-068</span>
                </div>
                <div>
                    <b>Preferências de atendimento</b>
                    <ul>
                        <li>Em Domicílio</li>
                    </ul>
                    <br />
                    <b>Raio de atendimento</b>
                    <ul>
                        <li>35 Km</li>
                    </ul>
                </div>
            </div>
        </div>
    );
}

export default CardInformacoesDoProfissional;