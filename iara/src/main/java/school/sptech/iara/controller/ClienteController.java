package school.sptech.iara.controller;

import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private List<Cliente> clientes = new ArrayList<>();

    // retorna todos registros de usuários
    @GetMapping
    public List<Cliente> getListaClientes(){
        return clientes;
    }

    // retorna usuário pelo index
    @GetMapping("/{index}")
    public Cliente getClientePorIndex(@PathVariable int index){
        try{
            if (!Objects.isNull(clientes.get(index))){
                return clientes.get(index);
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
        return null;
    }

    //cadastro de clientes, com possibilidade de cadastrar vários de uma só vez
    @PostMapping
    public void postCadastrarClientes(@RequestBody Cliente cliente[]){
        for (int i = 0; i < cliente.length; i++) {
            boolean encontrado = false;
            for (Cliente cl: clientes) {
                if (cl.getEmail().equalsIgnoreCase(cliente[i].getEmail())||
                    cl.getCpf().equalsIgnoreCase(cliente[i].getCpf())||
                    cl.getTelefone().equalsIgnoreCase(cliente[i].getTelefone())){
                    encontrado = true;
                }
            }
            if (!encontrado){
                clientes.add(cliente[i]);
            }
        }
    }

    //Adicionar avaliação a lista de avaliações
    @PostMapping("/avaliacao/{indexUser}/{avaliacao}")
    public String postAddAvaliacao(@PathVariable int indexUser, @PathVariable int avaliacao){
        try{
            if (avaliacao < 0 || avaliacao >5){
                return "A avaliação deve ser um número entre 0 e 5";
            }
            if (!Objects.isNull(clientes.get(indexUser))){
                clientes.get(indexUser).addAvaliacao(avaliacao);
                return "Avaliação de " + avaliacao + " atribuida para " + clientes.get(indexUser).getNome();
            }
        }catch (Exception e){
            System.out.println(e);
            return e.toString();
        }
        return null;
    }

    //Autenticar usuário
    @PostMapping("/autenticacao/{email}/{senha}")
    public String postAutenticarUsuario(@PathVariable String email, @PathVariable String senha){
        for (Cliente cliente: clientes) {
            if (cliente.usuarioExiste(email,senha)){
                if (!cliente.isAutenticado()){
                    cliente.autenticar(email,senha);
                    return "Usuário autenticado com sucesso";
                }else {
                    return "Usuário ja está autenticado";
                }
            }
        }
        return "Usuário não encontrado";
    }

//    desautenticar usuário
    @DeleteMapping("/autenticacao/{email}/{senha}")
    public String deleteDesautenticarUsuario(@PathVariable String email, @PathVariable String senha){
        for (Cliente cliente: clientes) {
            if (cliente.usuarioExiste(email,senha)){
                if (cliente.isAutenticado()){
                    cliente.logOff(email,senha);
                    return "Usuário desautenticado com sucesso";
                }else {
                    return "Usuário não está autenticado";
                }
            }
        }
        return "Usuário não encontrado";
    }
}
