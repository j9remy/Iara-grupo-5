import logo from '../html-css-template/img/logo-branco.png';
import api from "../api";
import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import { Link } from 'react-router-dom';

function CadastroInformacoesPessoais() {

    const maskTelefone = (value) => {
        return value
            .replace(/\D/g, "")
            .replace(/^(\d{2})(\d)/g, "($1) $2")
            .replace(/(\d)(\d{4})$/, "$1-$2");
    };

    const [nome, setNome] = useState('');
    const [sobrenome, setSobrenome] = useState('');
    const [cpf, setCpf] = useState('');
    const [dataNasc, setDataNasc] = useState('');
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [senhaVerificacao, setSenhaVerificacao] = useState('');
    const [telefone, setTelefone] = useState('');
    const [sexo, setSexo] = useState('');
    const [id, setId] = useState([]);
    const [idEndereco, setIdEndereco] = useState('');
    const [cep, setCep] = useState('');
    const [numero, setNumero] = useState('');
    const [complemento, setComplemento] = useState(null);

    const navigate = useNavigate();

    function SubmeterFormCliente(evento) {

        evento.preventDefault();
        api.get("/cliente").then((resposta) => {
            setId(resposta.data)
        })

        let jsonCliente = {
            nome: nome,
            sobrenome: sobrenome,
            cpf: cpf,
            dataNasc: dataNasc,
            email: email,
            senha: senha,
            telefone: telefone,
            sexo: sexo
        }

        if (senha !== senhaVerificacao) {
            alert("As senhas devem ser iguais!");
        } else {
            api.post('/cliente', jsonCliente, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(() => {
                alert("Restaurante cadastrado com sucesso!");
                navigate("/sucessoCadastro")
            });
        }

    }

    console.log(id)

    function SubmeterFormEndereco(evento) {

        let jsonEndereco = {
            cep: cep,
            numero: numero,
            complemento: complemento
        }

        if (senha !== senhaVerificacao) {
            alert("As senhas devem ser iguais!");
        } else {
            api.post('/endereco', jsonEndereco, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
        }
    }


    console.log(id)
    return (
        <>
            <div class="page dflex acenter jcenter txt-medium">
                <form id="cadastro-cliente" class="container" onSubmit={SubmeterFormCliente}>
                    <Link to={'/'}>
                        <img class="logo transform" src={logo} />
                    </Link>
                    <h2>Dados Pessoais</h2>
                    <div class="card bg-dark-red dflex jbetween fwrap">
                        <div class="user-input-wrp width-4 input-group">
                            <input
                                type="text"
                                class="input"
                                id="input-nome"
                                onChange={evento => setNome(evento.target.value)}
                                required
                            />
                            <label class="user-label">Nome</label>
                        </div>
                        <div class="user-input-wrp width-4 input-group">
                            <input
                                type="text"
                                class="input"
                                id="input-sobrenome"
                                onChange={evento => setSobrenome(evento.target.value)}
                                required
                            />
                            <label class="user-label">Sobrenome</label>
                        </div>
                        <div class="user-input-wrp width-4 input-group">
                            <input
                                type="text"
                                class="input"
                                id="input-genero"
                                onChange={evento => setSexo(evento.target.value)}
                                required
                            />
                            <label class="user-label">Gênero</label>
                        </div>
                        <div class="user-input-wrp width-4 input-group">
                            <input
                                type="date"
                                class="input"
                                id="input-nascimento"
                                onChange={evento => setDataNasc(evento.target.value)}
                                // onkeypress="$(this).mask('00/00/0000')"
                                required
                            />
                            <label class="user-label">Data de Nascimento</label>
                        </div>
                        <div class="user-input-wrp width-4 input-group">
                            <input
                                type="text"
                                class="input"
                                id="input-cpf"
                                onChange={evento => setCpf(evento.target.value)}
                                // onkeypress="$(this).mask('000.000.000-00')"
                                required
                            />
                            <label class="user-label">CPF</label>
                        </div>
                        <div class="user-input-wrp width-4 input-group">
                            <input
                                type="text"
                                class="input"
                                id="input-telefone"
                                value={telefone}
                                onChange={evento => setTelefone(maskTelefone(evento.target.value))}
                                mask='(00) 00000-0000'
                                required
                            />
                            <label class="user-label">Telefone</label>
                        </div>
                    </div>
                    <h2>Endereço</h2>
                    <div class="card bg-dark-red dflex jbetween fwrap" onSubmit={SubmeterFormEndereco}>
                        <div class="user-input-wrp width-4 input-group">
                            <input
                                type="text"
                                class="input"
                                id="input-cep"
                                onChange={evento => setCep(evento.target.value)}
                                required
                            />
                            <label class="user-label">CEP</label>
                        </div>
                        <div class="width-8"></div>
                        <div class="user-input-wrp width-5 input-group">
                            <input
                                type="text"
                                class="input"
                                id="input-logradouro"
                                required
                            />
                            <label class="user-label">Logradouro</label>
                        </div>
                        <div class="user-input-wrp width-2 input-group">
                            <input
                                type="text"
                                class="input"
                                id="input-numero"
                                required
                                onChange={evento => setNumero(evento.target.value)}
                            />
                            <label class="user-label">Número</label>
                        </div>
                        <div class="user-input-wrp width-5 input-group">
                            <input
                                type="text"
                                class="input"
                                id="input-complemento"
                                onChange={evento => setComplemento(evento.target.value) | setComplemento(null)}
                            />
                            <label class="user-label">Complemento</label>
                        </div>
                        <div class="user-input-wrp width-5 input-group">
                            <input
                                type="text"
                                class="input"
                                id="input-bairro"
                                required
                            />
                            <label class="user-label">Bairro</label>
                        </div>
                        <div class="user-input-wrp width-5 input-group">
                            <input
                                type="text"
                                class="input"
                                id="input-cidade"
                                required
                            />
                            <label class="user-label">Cidade</label>
                        </div>
                        <div class="user-input-wrp width-2 input-group">
                            <input
                                type="text"
                                class="input"
                                id="input-uf"
                                maxlength="2"
                                required
                                value={null}
                                oninput="this.value = this.value.toUpperCase()"
                            />
                            <label class="user-label">UF</label>
                        </div>
                    </div>

                    <h2>Autenticação</h2>
                    <div class="card bg-dark-red dflex jbetween fwrap">
                        <div class="user-input-wrp width-4 input-group">
                            <input
                                type="text"
                                class="input"
                                id="input-email"
                                onChange={evento => setEmail(evento.target.value)}
                                required />
                            <label class="user-label">E-mail</label>
                        </div>
                        <div class="user-input-wrp width-4 input-group">
                            <input
                                type="password"
                                class="input"
                                onChange={evento => setSenha(evento.target.value)}
                                id="input-senha"
                                required
                            />
                            <label class="user-label">Senha</label>
                        </div>
                        <div class="user-input-wrp width-4 input-group">
                            <input
                                type="password"
                                class="input"
                                id="input-confirmar-senha"
                                onChange={evento => setSenhaVerificacao(evento.target.value)}
                                required
                            />
                            <label class="user-label">Confirmar Senha</label>
                        </div>
                    </div>
                    <button type="submit" class="button bg-dark-red txt-white bg-hover-white txt-hover-dark-red align-right shadow-smooth">CADASTRAR</button>
                </form>
            </div>
        </>
    )
}
export default CadastroInformacoesPessoais;