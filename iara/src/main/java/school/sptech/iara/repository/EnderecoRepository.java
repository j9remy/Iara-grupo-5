package school.sptech.iara.repository;

import lombok.ToString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.sptech.iara.model.Endereco;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    @Query("SELECT e FROM Endereco e WHERE e.cep=?1 AND e.complemento = ?2 AND e.numero = ?3")
    List<Endereco> enderecoValido(String cep, String complemento, String numero);
}
