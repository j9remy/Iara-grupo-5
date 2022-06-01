package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.iara.model.Cliente;


import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByEmailAndSenha(String email, String senha);

    @Query("SELECT c FROM Cliente c WHERE c.email = ?1 OR c.cpf = ?2 OR c.telefone = ?3")
    List<Cliente> validarCadastro(String email, String cpf, String telefone);

    /*@Query("UPDATE Cliente a SET a.foto = ?2 WHERE a.id = ?1")
    @Modifying
    @Transactional
    void atualizarFoto(Integer idCliente, byte[] novaFoto);*/

    @Query("SELECT a.foto FROM Cliente a WHERE a.id = ?1")
    byte[] getFoto(Integer idCliente);

    Boolean existsByTelefone(String telefone);
}
