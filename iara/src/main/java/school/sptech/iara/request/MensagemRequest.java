package school.sptech.iara.request;

import org.jetbrains.annotations.NotNull;
import school.sptech.iara.model.Chat;
import school.sptech.iara.model.Cliente;
import school.sptech.iara.model.Prestador;

import javax.validation.constraints.NotBlank;

public class MensagemRequest {
    @NotNull
    private Integer idChat;
    @NotBlank
    private String mensagem;
    @NotNull
    private Boolean enviadoPeloPrestador;


    public Integer getIdChat() {
        return idChat;
    }
    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    public Boolean getEnviadoPeloPrestador() {
        return enviadoPeloPrestador;
    }
    public void setEnviadoPeloPrestador(Boolean enviadoPeloPrestador) {
        this.enviadoPeloPrestador = enviadoPeloPrestador;
    }
}
