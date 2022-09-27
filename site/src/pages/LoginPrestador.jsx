import { Link } from 'react-router-dom';
import logo from '../html-css-template/img/logo-red.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFacebookF, faGoogle } from '@fortawesome/free-brands-svg-icons';
import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import api from "../api";

function ReiniciarValores() {
    return { email: "", senha: "" }
}

function LoginPrestador() {
    const [values, setValues] = useState(ReiniciarValores);
    const navigate = useNavigate();
    const swal = withReactContent(Swal);

    function verificarValues(evento) {
        const { value, name } = evento.target;
        setValues({ ...values, [name]: value, })
    }
    function autenticarLogin(evento) {
        evento.preventDefault();
        api.get(`/prestador/logar/${values.email}/${values.senha}`, values,
            {
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        ).then((res) => {
            localStorage.dadosPrestador = res.data;
            localStorage.idPrestador = res.data.id;

            console.log(res.data);
            console.log(localStorage.idPrestador);
            navigate("/accountProfissional")
        },
        )
            .catch(error => {
                if (error.request.status === 401) {
                    console.log("success")
                    swal.fire({
                        icon: "error",
                        title: <h1>Ops... Dados inválidos</h1>,
                        text: "Por favor, tente novamente!"
                    });
                }
            });
    }

    return (
        <>
            <div class="page dflex acenter jcenter">
                <div class="card bg-dark-red txt-left">
                    <Link to={'/'}>
                        <img class="logo transform" src={logo} />
                    </Link>
                    <form id="login" class="campos dflex fdcolumn txt-medium" onSubmit={autenticarLogin}>
                        <div class="input-group">
                            <input
                                required
                                id="input-email"
                                name="email"
                                type="text"
                                value={values.email}
                                onChange={verificarValues}
                                autocomplete="off"
                                class="input"
                            />
                            <label class="user-label">E-mail</label>
                        </div>
                        <div class="input-group">
                            <input required
                                id="input-senha"
                                name="senha"
                                type="password"
                                value={values.senha}
                                onChange={verificarValues}
                                autocomplete="off"
                                class="input" />
                            <label class="user-label">Senha</label>
                        </div>

                        <button class="button bg-white txt-black bg-hover-dark-red txt-hover-white" type="submit">ENTRAR</button>
                    </form>
                    <div class="dflex acenter jcenter">
                        <button class="button circle dflex acenter jcenter transform" aria-label="Login com Google" type="button">
                            <FontAwesomeIcon icon={faGoogle} />
                        </button>

                        <button class="button circle dflex acenter jcenter transform" aria-label="Login com facebook" type="button">
                            <FontAwesomeIcon icon={faFacebookF} />
                        </button>
                    </div>

                    <div class="txt-white dflex acenter jcenter login">
                        Ainda não possui cadastro? <Link class="txt-white transform" to="/escolhaCadastro"><u><b>Cadastre-se aqui</b></u></Link>
                    </div>
                </div>
            </div>
        </>
    );

}

export default LoginPrestador;