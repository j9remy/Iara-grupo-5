function CardInformacoesCliente(){

    return(
        <div class="card prelative">
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

export default CardInformacoesCliente;