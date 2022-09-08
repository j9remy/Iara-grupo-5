import logo from '../html-css-template/img/logo-branco.png';
import React, { useState, useEffect } from 'react';
import api from "../api";
import { GoTrashcan } from "react-icons/go";
import SelectCategoria from './SelectCategoria';
import { Link } from 'react-router-dom';


function CadastroDeHabilidades(props) {

    const [categoria, setCategoria] = useState([]);

    useEffect(() => {
        try {
            api.get("categoria/").then((resposta) => {
                console.log(resposta.data)
                setCategoria(resposta.data)
            })
        } catch (erro) {
            console.error(erro);
        }

    }, [])
    return (
        <div class="page dflex acenter jcenter txt-medium">
            <form id="cadastro-cliente" class="container">
                <div href="" class="logo transform prelative">
                    <img src={logo} />
                    <span class="subtitulo">CADASTRO</span>
                </div>
                <h2>Cadastro Profissional</h2>
                <div class="dflex jbetween fwrap">
                    <div class="dflex fwrap astart jaround width-100-porc">
                        <div class="width-50-margin-20">
                            <h3>Cadastrar Serviços</h3>
                            <div class="dflex fwrap jbetween">
                                <select class="input width-50-margin-10 margin-bottom-15">
                                    <option value="" hidden="true" default="true">Escolha a Categoria</option>
                                    <option value="">Corte Cabelo</option>
                                    <option value="">Hidratação</option>
                                    <option value="">Maquiagem</option>
                                    <option value="">Manicure</option>
                                    <option value="">Design de sobrancelhas</option>
                                    <option value="">Massagem</option>
                                    <option value="">Pedicure</option>
                                </select>
                                <input type="text" placeholder="Escolha a Especialidade" step="any" class="input width-50-margin-10 margin-bottom-15" />
                                <input type="number" min="1" step="any" placeholder="Preço (R$)" id="input-preco" class="input width-50-margin-10 margin-bottom-15" />
                                <input type="text" placeholder="Duração" class="input width-50-margin-10" id="input-duracao-servico" onkeypress="$(this).mask('00:00')" />
                            </div>
                            <button type="submit" class="button button-cadastro-profissional txt-white bg-hover-white txt-hover-dark-red">CADASTRAR</button>
                        </div>
                        <div class="width-50-margin-20">
                            <h3>Serviços Cadastrados</h3>
                            <div class="table">
                                <table>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>
                                            <button type="button" class="trash">

                                            </button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>
                                            <button type="button" class="trash">

                                            </button>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="separador"></div>
                    <div class="dflex fwrap astart jaround width-100-porc">
                        <div class="width-50-margin-20">
                            <h3>Dias de Atendimento</h3>
                            <div class="dflex fwrap">
                                <label class="label-checkbox">
                                    <input type="checkbox" name="Segunda-Feira" />
                                    <div class="checkmark"></div>
                                    <span>Segunda-Feira</span>
                                </label>
                                <label class="label-checkbox">
                                    <input type="checkbox" name="Terça-Feira" />
                                    <div class="checkmark"></div>
                                    <span>Terça-Feira</span>
                                </label>
                                <label class="label-checkbox">
                                    <input type="checkbox" name="Quarta-Feira" />
                                    <div class="checkmark"></div>
                                    <span>Quarta-Feira</span>
                                </label>
                                <label class="label-checkbox">
                                    <input type="checkbox" name="Quinta-Feira" />
                                    <div class="checkmark"></div>
                                    <span>Quinta-Feira</span>
                                </label>
                                <label class="label-checkbox">
                                    <input type="checkbox" name="Sexta-Feira" />
                                    <div class="checkmark"></div>
                                    <span>Sexta-Feira</span>
                                </label>
                                <label class="label-checkbox">
                                    <input type="checkbox" name="Sábado" />
                                    <div class="checkmark"></div>
                                    <span>Sábado</span>
                                </label>
                                <label class="label-checkbox">
                                    <input type="checkbox" name="Domingo" />
                                    <div class="checkmark"></div>
                                    <span>Domingo</span>
                                </label>
                            </div>
                        </div>
                        <div class="width-50-margin-20">
                            <h3 class="margin-bottom-10">Horário de Atendimento</h3>
                            <div class="dflex acenter jbetween margin-bottom-15">
                                <input type="text" placeholder="Duração" class="input width-40-porc" id="atendimento-inicial" onkeypress="$(this).mask('00:00')" />
                                <span>até</span>
                                <input type="text" placeholder="Duração" class="input width-40-porc" id="atendimento-final" onkeypress="$(this).mask('00:00')" />
                            </div>
                            <h3 class="margin-bottom-10">Horário de Pausa</h3>
                            <div class="dflex acenter jbetween">
                                <input type="text" placeholder="Duração" class="input width-40-porc" id="pausa-inicial" onkeypress="$(this).mask('00:00')" />
                                <span>até</span>
                                <input type="text" placeholder="Duração" class="input width-40-porc" id="pausa-final" onkeypress="$(this).mask('00:00')" />
                            </div>
                        </div>
                    </div>
                    <div class="separador"></div>
                    <div class="dflex fwrap astart jaround width-100-porc">
                        <div class="width-50-margin-20">
                            <h3>Preferência de Atendimento</h3>
                            <div class="dflex fwrap">
                                <label class="label-checkbox wide">
                                    <input type="checkbox" id="checkbox-estabelecimento" name="Estabelecimento Próprio" />
                                    <div class="checkmark"></div>
                                    <span>Estabelecimento Próprio</span>
                                </label>
                                <label class="label-checkbox wide">
                                    <input type="checkbox" id="checkbox-domicilio" name="Em Domicílio" />
                                    <div class="checkmark"></div>
                                    <span>Em Domicílio</span>
                                </label>
                            </div>
                        </div>
                        <div class="width-50-margin-20">
                            <h3 class="margin-bottom-10">Raio de Atentimento em Domicílio</h3>
                            <div class="dflex jbetween">
                                <div id="div-range" class="range-parent width-100-porc disabled">
                                    <input type="number" min="1" max="100" step="any" placeholder="Raio de 0 Km até 100 Km" class="input width-50-margin-10" id="range" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="dflex acenter jbetween">
                    <Link to={"/perfilProfissionalColaborador"}>
                        <a href="cadastroDados.html" class="button bg-dark-red txt-white bg-hover-white txt-hover-dark-red shadow-smooth">
                            <i class="fa-solid fa-arrow-left-long margin-right-5"></i>
                            VOLTAR
                        </a>
                    </Link>
                    <button type="submit" class="button bg-dark-red txt-white bg-hover-white txt-hover-dark-red shadow-smooth">CADASTRAR</button>
                </div>
            </form>
        </div>

    );
}

export default CadastroDeHabilidades;