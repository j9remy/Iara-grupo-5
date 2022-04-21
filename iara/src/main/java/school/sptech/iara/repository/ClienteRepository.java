package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.sptech.iara.model.Cliente;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByEmailAndSenha(String email, String senha);

    @Query("SELECT c FROM Cliente c WHERE email = ?1 OR cpf = ?2 OR telefone = ?3")
    List<Cliente> validarCadastro(String email, String cpf, String telefone);
}
