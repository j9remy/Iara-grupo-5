package school.sptech.iara.request;

import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UsuarioEmailSenhaRequest {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 5, max = 20)
    private String senha;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
