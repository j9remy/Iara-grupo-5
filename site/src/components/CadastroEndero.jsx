import logo from '../html-css-template/img/logo-branco.png';
import api from "../api";
import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";

function CadastroEndereco() {

    const [cep, setCep] = useState('');
    const [numero, setNumero] = useState('');
    const [complemento, setComplemento] = useState(null);
    function SubmeterFormEndereco(e) {

        let jsonEndereco = {
            cep: cep,
            numero: numero,
            complemento: complemento
        }
    }

    return (
        <>
            <form id="cadastro-cliente" class="container">
                <a to={'/'} class="logo transform prelative">
                    <img src={logo} />
                    <span class="subtitulo">CADASTRO</span>
                </a>
                <h2>Endereço</h2>
                <div class="card bg-off-white dflex jbetween fwrap" onSubmit={SubmeterFormEndereco}>
                    <div class="user-input-wrp width-4 input-group">
                        <input
                            type="text"
                            class="input"
                            id="input-cep"
                            onChange={e => setCep(e.target.value)}
                            maxLength="8" />
                        <label class="user-label">CEP</label>
                    </div>
                    <div class="width-8"></div>
                    <div class="user-input-wrp width-5 input-group">
                        <input
                            type="text"
                            class="input"
                            id="input-logradouro" />
                        <label class="user-label">Logradouro</label>
                    </div>
                    <div class="user-input-wrp width-2 input-group">
                        <input
                            type="text"
                            class="input"
                            id="input-numero"
                            maxLength="5"
                            onChange={e => setNumero(e.target.value)} />
                        <label class="user-label">Número</label>
                    </div>
                    <div class="user-input-wrp width-5 input-group">
                        <input
                            type="text"
                            class="input"
                            id="input-complemento"
                            onChange={e => setComplemento(e.target.value) | setComplemento(null)} />
                        <label class="user-label">Complemento</label>
                    </div>
                    <div class="user-input-wrp width-5 input-group">
                        <input
                            type="text"
                            class="input"
                            id="input-bairro" />
                        <label class="user-label">Bairro</label>
                    </div>
                    <div class="user-input-wrp width-5 input-group">
                        <input
                            type="text"
                            class="input"
                            id="input-cidade" />
                        <label class="user-label">Cidade</label>
                    </div>
                    <div class="user-input-wrp width-2 input-group">
                        <input
                            type="text"
                            class="input"
                            id="input-uf"
                            maxlength="2"
                            value={null}
                            oninput="this.value = this.value.toUpperCase()" />
                        <label class="user-label">UF</label>
                    </div>
                </div>
                <button type="submit" class="button bg-dark-red txt-white bg-hover-white txt-hover-dark-red align-right shadow-smooth">CADASTRAR</button>
            </form>
        </>
    )
}

export default CadastroEndereco;