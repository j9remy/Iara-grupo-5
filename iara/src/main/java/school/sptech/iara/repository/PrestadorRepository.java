package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.iara.model.Cliente;
import school.sptech.iara.model.Prestador;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PrestadorRepository extends JpaRepository<Prestador, Integer> {
    boolean existsByEmailAndSenha(String email, String senha);

    Prestador getByEmailAndSenha(String email, String senha);

    @Query("SELECT c FROM Prestador c WHERE email = ?1 OR cpf = ?2 OR telefone = ?3")
    List<Prestador> validarCadastro(String email, String cpf, String telefone);
/*
    @Query("UPDATE Prestador a SET a.foto = ?2 WHERE a.idPrestador = ?1")
    @Modifying
    @Transactional
    void atualizarFoto(Integer idPrestador, byte[] novaFoto);

    @Query("SELECT a.foto FROM Prestador c WHERE a.idPrestador = ?1")
    byte[] getFoto(Integer idPrestador);
 */
    Optional<Prestador> findByEmailAndSenha(String email, String senha);

    @Query("UPDATE Prestador a SET a.foto = ?2 WHERE a.id = ?1")
    @Modifying
    @Transactional
    void atualizarFoto(Integer idPrestador, byte[] novaFoto);

    @Query("SELECT a.foto FROM Prestador c WHERE a.id = ?1")
    byte[] getFoto(Integer idPrestador);

    Boolean existsByTelefone(String telefone);

}
