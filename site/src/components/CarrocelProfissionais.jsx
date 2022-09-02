function CarrocelProfissionais(props) {
    return (
      <>
        <div class="item">
          <div class="card bg-dark-red txt-white txt-center">
            <img src={props.foto} />
            <div class="nome">
              <b>{props.nome}</b>
              <br />
              {props.habilidade}
            </div>
            <div class="distancia">{props.distancia}</div>
            <div
              class="avaliacao"
              data-aval={`${props.avaliacao} estrelas`}
            >
              {props.avaliacao} estrelas
            </div>
            <a
              class="button bg-white txt-dark-red bg-hover-dark-red txt-hover-white"
              href=" "
            >
              AGENDAR
            </a>
          </div>
        </div>
      </>
    );
  }
  
  export default CarrocelProfissionais;