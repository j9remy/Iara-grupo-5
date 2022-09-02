package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Categoria;
import school.sptech.iara.model.Habilidade;

import java.util.Optional;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Integer> {

    boolean existsByDescricao(String desc);
    boolean existsByCategoria(Categoria cat);
    boolean existsByCategoriaAndDescricao(Categoria categoria, String descricao);
    Optional<Habilidade> findByCategoriaAndDescricao(Categoria categoria, String descricao);


//    Boolean existsByHabilidadeAndDescricao(String habilidade, String descricao);
}
