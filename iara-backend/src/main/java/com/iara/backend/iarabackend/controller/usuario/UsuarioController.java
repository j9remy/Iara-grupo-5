package com.iara.backend.iarabackend.controller.usuario;

import org.springframework.web.bind.annotation.*;
import com.iara.backend.iarabackend.models.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    List<Usuario> usuarios = new ArrayList<Usuario>();

    // POST /usuarios
    @PostMapping
    public String postUsuario(@RequestBody Usuario novoUsuario){
        for (Usuario user : usuarios){
            if (user.getNome().equalsIgnoreCase(novoUsuario.getNome())
                && user.getSenha().equalsIgnoreCase(novoUsuario.getSenha()))
                return "Usuario já cadastrado na base de dados";
        }
        usuarios.add(novoUsuario);
        return "Usuario "+novoUsuario.getNome()+ " cadastrado no sistema";
    }

    //POST /usuarios/autenticacao/{usuario}/{senha}
    @PostMapping("/autenticacao/{usuario}/{senha}")
    public String postUsuarioAutenticacao(
        @PathVariable String usuario,
        @PathVariable String senha
        ){
        for (Usuario user : usuarios){
            if (user.getUsuario().equalsIgnoreCase(usuario) && user.getSenha().equalsIgnoreCase(senha))
                user.setAutenticado(true);
                return "Usuario "+ user.getNome() +" agora está autenticado";
        }
        return "Usuarios "+usuario+ " Não encontrado";
    }

    // GET/usuario
    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    //DELETE /usuarios/autenticacao/{usuario}
    @DeleteMapping("/autenticacao/{usuario}/")
    public String logoffUsuario(@PathVariable String usuario){
        for (Usuario user : usuarios){
            if (user.getUsuario().equalsIgnoreCase(usuario)) {
                if (user.getAutenticado()) {
                    user.setAutenticado(false);
                    return "Logoff do usuario " + user.getNome() + " concluído";
                }else{
                    return "Usuario" + user.getNome() + " NÃO está autenticado";
                }
            }
        }
        return "Usuario "+ usuario +" não encontrado";
    }

    //Eu criei um endPoint que retorna um usuario dado um indice especifico
    @GetMapping("/{index}")
    public Usuario getUsuarios(@PathVariable int index) {
        return usuarios.get(index);
    }
    
}

