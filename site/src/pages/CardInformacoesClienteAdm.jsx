import { faPen } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';

function CardInformacoesClienteAdm(){

    return(
        <div class="card prelative">
            <Link to={"/cadastroCliente"}>
                <a class="btn-editar-perfil pabsolute bg-hover-white txt-hover-dark-red transform">
                    <FontAwesomeIcon icon={faPen} />
                </a>
            </Link>
            <div class="dflex acenter jbetween">
                <div class="dflex acenter jbetween">
                    <img src="../img/foto-perfil.png" alt="Foto de perfil" class="margin-right-twenty" />
                    <div>
                        <b>Isabela Santos, 27 anos</b><br />
                    </div>
                </div>
                <div class="dflex acenter jbetween">
                    <div class="button bg-red txt-white bg-hover-white txt-hover-dark-red margin-none dflex acenter jbetween"><span>4</span>/5 <i class="icon star"></i></div>
                </div>
            </div>
            <div class="margin-top-thirty dflex jbetween width-50-porc">
                <div>
                    <b>Telefone</b><br />
                    <span>(11) 99127-0357</span><br /><br />
                    <b>Gênero</b><br />
                    <span>Feminino</span>
                </div>
                <div>
                    <b>Endereço</b><br />
                    <span>Av. Brasil, n°127<br />
                    Cerqueira César, São Paulo<br />
                    SP - 01414-001</span>                    
                </div>
            </div>
        </div>
    )

}
export default CardInformacoesClienteAdm;