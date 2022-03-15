package school.sptech.iara.controller;

import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Cliente;
import school.sptech.iara.model.Habilidade;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.model.Servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/prestador")
public class PrestadorController {

    private List<Prestador> prestadores = new ArrayList<>();
    private List<Habilidade> habilidades = new ArrayList<>();
    private List<Servico> servicos = new ArrayList<>();

    // retorna todos registros de usuários
    @GetMapping
    public List<Prestador> getListaPrestadores(){
        return prestadores;
    }

    // retorna usuário pelo index
    @GetMapping("/{index}")
    public Prestador getPrestadorPorIndex(@PathVariable int index){
        try{
            if (!Objects.isNull(prestadores.get(index))){
                return prestadores.get(index);
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
        return null;
    }

    //cadastro de clientes, com possibilidade de cadastrar vários de uma só vez
    @PostMapping
    public void postCadastrarPrestadores(@RequestBody Prestador prestador[]){
        for (int i = 0; i < prestador.length; i++) {
            boolean encontrado = false;
            for (Prestador pr: prestadores) {
                if (pr.getEmail().equalsIgnoreCase(prestador[i].getEmail())||
                        pr.getCpf().equalsIgnoreCase(prestador[i].getCpf())||
                        pr.getTelefone().equalsIgnoreCase(prestador[i].getTelefone())){
                    encontrado = true;
                }
            }
            if (!encontrado){
                prestadores.add(prestador[i]);
            }
        }
    }

    //Autenticar usuário
    @PostMapping("/autenticacao/{email}/{senha}")
    public String postAutenticarUsuario(@PathVariable String email, @PathVariable String senha){
        for (Prestador prestador: prestadores) {
            if (prestador.usuarioExiste(email,senha)){
                if (!prestador.isAutenticado()){
                    prestador.autenticar(email,senha);
                    return "Usuário autenticado com sucesso";
                }else {
                    return "Usuário ja está autenticado";
                }
            }
        }
        return "Usuário não encontrado";
    }

    // adiciona habilidade
    @PostMapping("/habilidade/{index}")
    public void postAddHabilidade(@RequestBody Habilidade habilidade,
                                  @PathVariable int index){
        boolean encontrado = false;
        for (Habilidade hab: habilidades) {
            if (hab.toString().equals(habilidade.toString())){
                encontrado = true;
            }
        }
        if (!encontrado){
            try{
                if (!Objects.isNull(prestadores.get(index))){
                    prestadores.get(index).addHabilidade(habilidade);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    //adiciona serviço
    @PostMapping("/servico/{index}")
    public void postAddServico(@RequestBody Servico servico,
                                  @PathVariable int index){
        boolean encontrado = false;
        for (Servico ser: servicos) {
            if (ser.toString().equals(servico.toString())){
                encontrado = true;
            }
        }
        if (!encontrado){
            try{
                if (!Objects.isNull(prestadores.get(index))){
                    prestadores.get(index).addServico(servico);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    //    desautenticar usuário
    @DeleteMapping("/autenticacao/{email}/{senha}")
    public String deleteDesautenticarUsuario(@PathVariable String email, @PathVariable String senha){
        for (Prestador prestador: prestadores) {
            if (prestador.usuarioExiste(email,senha)){
                if (prestador.isAutenticado()){
                    prestador.logOff(email,senha);
                    return "Usuário desautenticado com sucesso";
                }else {
                    return "Usuário não está autenticado";
                }
            }
        }
        return "Usuário não encontrado";
    }


}
