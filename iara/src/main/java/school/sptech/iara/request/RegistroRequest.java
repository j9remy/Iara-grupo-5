package school.sptech.iara.request;

import school.sptech.iara.model.Cliente;
import school.sptech.iara.model.Prestador;
import java.util.List;

public class RegistroRequest {

    private String nomeArq;
    private List<Cliente> listaCliente;
    private List<Prestador> listaPrestador;

    public String getNomeArq() {
        return nomeArq;
    }

    public void setNomeArq(String nomeArq) {
        this.nomeArq = nomeArq;
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public List<Prestador> getListaPrestador() {
        return listaPrestador;
    }

    public void setListaPrestador(List<Prestador> listaPrestador) {
        this.listaPrestador = listaPrestador;
    }
}
