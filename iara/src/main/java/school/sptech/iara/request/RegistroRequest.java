package school.sptech.iara.request;

import school.sptech.iara.model.Cliente;

import java.util.List;

public class RegistroRequest {

    private String nomeArq;
    private List<Cliente> lista;

    public String getNomeArq() {
        return nomeArq;
    }

    public void setNomeArq(String nomeArq) {
        this.nomeArq = nomeArq;
    }

    public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }
}
