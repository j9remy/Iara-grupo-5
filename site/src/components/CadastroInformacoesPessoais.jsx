import logo from '../html-css-template/img/logo-branco.png';

function CadastroInformacoesPessoais() {
    var cep = document.getElementById('input-cep');
    var logradouro = document.getElementById('input-logradouro');
    var bairro = document.getElementById('input-bairro');
    var cidade = document.getElementById('input-cidade');
    var uf = document.getElementById('input-uf');
    
    function limpa_formulário_cep() {
        logradouro.value=("");
        bairro.value=("");
        cidade.value=("");
        uf.value=("");
        //div_mens_cep.style.display='none'
    }

    function meu_callback(conteudo) {
        if (!("erro" in conteudo)) {
            if(conteudo.logradouro != ""){
                logradouro.value=(conteudo.logradouro);
                logradouro.disabled = true;
            }
            if(conteudo.bairro != ""){
                bairro.value=(conteudo.bairro);
                bairro.disabled = true;    
            }     
            if(conteudo.localidade != ""){   
                cidade.value=(conteudo.localidade);       
                cidade.disabled = true;  
            }
            if(conteudo.uf != ""){    
                uf.value=(conteudo.uf);
                uf.disabled = true;  
            }
            //div_mens_cep.style.display='none'
        }
        else {
            limpa_formulário_cep();
            //div_mens_cep.style.display='block';
        }
    }

    function pesquisacep(valor) {

        //Nova variável "cep" somente com dígitos.
        var cep = valor.replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "") {

        //Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;

        //Valida o formato do CEP.
        if(validacep.test(cep)) {

            cep.value = cep.substring(0,5)
            +"-"
            +cep.substring(5);

            /*Preenche os campos com "..." enquanto consulta webservice.
            logradouro.value="...";
            bairro.value="...";
            cidade.value="...";
            uf.value="...";
            */
            //div_mens_cep.style.display='none'

            //Cria um elemento javascript.
            var script = document.createElement('script');

            //Sincroniza com o callback.
            script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

            //Insere script no documento e carrega o conteúdo.
            document.body.appendChild(script);
         }

        else {
            limpa_formulário_cep();
            //div_mens_cep.style.display= 'block';
        }
    } 
    else {
        limpa_formulário_cep();
    }
};
    return (
        <div class="page dflex acenter jcenter txt-medium">
            <form id="cadastro-cliente" class="container">
                <a href="institucional.html" class="logo transform">
                    <img alt="Logo" src={logo} />
                </a>
                <h2>Dados Pessoais</h2>
                <div class="card bg-dark-red dflex jbetween fwrap">
                    <div class="user-input-wrp width-4">
                        <input type="text" class="input" id="input-nome" onkeyup="verificaConteudo(this)" required />
                        <span class="floating-label">Nome</span>
                    </div>
                    <div class="user-input-wrp width-4">
                        <input type="text" class="input" id="input-sobrenome" onkeyup="verificaConteudo(this)" required />
                        <span class="floating-label">Sobrenome</span>
                    </div>
                    <div class="user-input-wrp width-4">
                        <input type="text" class="input" id="input-genero" onkeyup="verificaConteudo(this)" required />
                        <span class="floating-label">Gênero</span>
                    </div>
                    <div class="user-input-wrp width-4">
                        <input type="text" class="input" id="input-nascimento" onkeyup="verificaConteudo(this)" onkeypress="$(this).mask('00/00/0000')" required />
                        <span class="floating-label">Data de Nascimento</span>
                    </div>
                    <div class="user-input-wrp width-4">
                        <input type="text" class="input" id="input-cpf" onkeyup="verificaConteudo(this)" onkeypress="$(this).mask('000.000.000-00')" required />
                        <span class="floating-label">CPF</span>
                    </div>
                    <div class="user-input-wrp width-4">
                        <input type="text" class="input" id="input-telefone" onkeyup="verificaConteudo(this)" onkeypress="$(this).mask('(00) 00000-0000')" required />
                        <span class="floating-label">Telefone</span>
                    </div>
                </div>
                <h2>Endereço</h2>
                <div class="card bg-dark-red dflex jbetween fwrap">
                    <div class="user-input-wrp width-4">
                        <input type="text" class="input" id="input-cep" onkeyup="verificaConteudo(this)" onkeypress="$(this).mask('00000-000')" onblur="pesquisacep(this.value);" required />
                        <span class="floating-label">CEP</span>
                    </div>
                    <div class="width-8"></div>
                    <div class="user-input-wrp width-5">
                        <input type="text" class="input" id="input-logradouro" onkeyup="verificaConteudo(this)" required />
                        <span class="floating-label">Logradouro</span>
                    </div>
                    <div class="user-input-wrp width-2">
                        <input type="text" class="input" id="input-numero" onkeyup="verificaConteudo(this)" required />
                        <span class="floating-label">Número</span>
                    </div>
                    <div class="user-input-wrp width-5">
                        <input type="text" class="input" id="input-complemento" onkeyup="verificaConteudo(this)" required />
                        <span class="floating-label">Complemento</span>
                    </div>
                    <div class="user-input-wrp width-5">
                        <input type="text" class="input" id="input-bairro" onkeyup="verificaConteudo(this)" required />
                        <span class="floating-label">Bairro</span>
                    </div>
                    <div class="user-input-wrp width-5">
                        <input type="text" class="input" id="input-cidade" onkeyup="verificaConteudo(this)" required />
                        <span class="floating-label">Cidade</span>
                    </div>
                    <div class="user-input-wrp width-2">
                        <input type="text" class="input" id="input-uf" onkeyup="verificaConteudo(this)" maxlength="2" oninput="this.value = this.value.toUpperCase()" required />
                        <span class="floating-label">UF</span>
                    </div>
                </div>
                <h2>Autenticação</h2>
                <div class="card bg-dark-red dflex jbetween fwrap">
                    <div class="user-input-wrp width-4">
                        <input type="email" class="input" id="input-email" onkeyup="verificaConteudo(this)" required />
                        <span class="floating-label">E-mail</span>
                    </div>
                    <div class="user-input-wrp width-4">
                        <input type="password" class="input" id="input-senha" onkeyup="verificaConteudo(this)" required />
                        <span class="floating-label">Senha</span>
                    </div>
                    <div class="user-input-wrp width-4">
                        <input type="password" class="input" id="input-confirmar-senha" onkeyup="verificaConteudo(this)" required />
                        <span class="floating-label">Confirmar Senha</span>
                    </div>
                </div>
                <button type="submit" class="button bg-dark-red txt-white bg-hover-white txt-hover-dark-red align-right shadow-smooth">CADASTRAR</button>
            </form>
        </div>
    );
}

export default CadastroInformacoesPessoais;