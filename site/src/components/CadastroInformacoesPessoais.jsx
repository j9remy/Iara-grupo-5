import { render } from '@testing-library/react';
import logo from '../html-css-template/img/logo-branco.png';



function CadastroInformacoesPessoais() {
    return (
        <>
            <div class="page dflex acenter jcenter txt-medium">
                <form id="cadastro-cliente" class="container">
                    <a href="institucional.html" class="logo transform">
                        <img alt="Logo" src={logo} />
                    </a>
                    <h2>Dados Pessoais</h2>
                    <div class="card bg-dark-red dflex jbetween fwrap">
                        <div class="user-input-wrp width-4 input-group">
                            <input type="text" class="input" id="input-nome" autocomplete="off" required />
                            <label class="user-label">Nome</label>
                        </div>
                        <div class="user-input-wrp width-4 input-group">
                            <input type="text" class="input" id="input-sobrenome" autocomplete="off" required />
                            <label class="user-label">Sobrenome</label>
                        </div>
                        <div class="user-input-wrp width-4 input-group">
                            <input type="text" class="input" id="input-genero" required />
                            <label class="user-label">Gênero</label>
                        </div>
                        <div class="user-input-wrp width-4 input-group">
                            <input type="text" class="input" id="input-nascimento" onkeypress="$(this).mask('00/00/0000')" required />
                            <label class="user-label">Data de Nascimento</label>
                        </div>
                        <div class="user-input-wrp width-4 input-group">
                            <input type="text" class="input" id="input-cpf" onkeypress="$(this).mask('000.000.000-00')" required />
                            <label class="user-label">CPF</label>
                        </div>
                        <div class="user-input-wrp width-4 input-group">
                            <input type="text" class="input" id="input-telefone" autocomplete="off" onkeypress="$(this).mask('(00) 00000-0000')" required />
                            <label class="user-label">Telefone</label>
                        </div>
                    </div>
                    <h2>Endereço</h2>
                    <div class="card bg-dark-red dflex jbetween fwrap">
                        <div class="user-input-wrp width-4 input-group">
                            <input type="text" class="input" id="input-cep" onkeypress="$(this).mask('00000-000')" onblur="pesquisacep(this.value);" required />
                            <label class="user-label">CEP</label>
                        </div>
                        <div class="width-8"></div>
                        <div class="user-input-wrp width-5 input-group">
                            <input type="text" class="input" id="input-logradouro" required />
                            <label class="user-label">Logradouro</label>
                        </div>
                        <div class="user-input-wrp width-2 input-group">
                            <input type="text" class="input" id="input-numero" required />
                            <label class="user-label">Número</label>
                        </div>
                        <div class="user-input-wrp width-5 input-group">
                            <input type="text" class="input" id="input-complemento" required />
                            <label class="user-label">Complemento</label>
                        </div>
                        <div class="user-input-wrp width-5 input-group">
                            <input type="text" class="input" id="input-bairro" required />
                            <label class="user-label">Bairro</label>
                        </div>
                        <div class="user-input-wrp width-5 input-group">
                            <input type="text" class="input" id="input-cidade" required />
                            <label class="user-label">Cidade</label>
                        </div>
                        <div class="user-input-wrp width-2 input-group">
                            <input type="text" class="input" id="input-uf" maxlength="2" oninput="this.value = this.value.toUpperCase()" required />
                            <label class="user-label">UF</label>
                        </div>
                    </div>
                    <h2>Autenticação</h2>
                    <div class="card bg-dark-red dflex jbetween fwrap">
                        <div class="user-input-wrp width-4 input-group">
                            <input type="text" class="input" autocomplete="off" id="input-email" required />
                            <label class="user-label">E-mail</label>
                        </div>
                        <div class="user-input-wrp width-4 input-group">
                            <input type="password" class="input" id="input-senha" required />
                            <label class="user-label">Senha</label>
                        </div>
                        <div class="user-input-wrp width-4 input-group">
                            <input type="password" class="input" id="input-confirmar-senha" required />
                            <label class="user-label">Confirmar Senha</label>
                        </div>
                    </div>
                    <button type="submit" class="button bg-dark-red txt-white bg-hover-white txt-hover-dark-red align-right shadow-smooth">CADASTRAR</button>
                </form>
            </div>
        </>
    );

}

export default CadastroInformacoesPessoais;