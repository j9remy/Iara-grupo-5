package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Categoria;
import school.sptech.iara.model.Habilidade;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Integer> {

    boolean existsByDescricao(String desc);
    boolean existsByCategoria(Categoria hab);
//    Boolean existsByHabilidadeAndDescricao(String habilidade, String descricao);
}
