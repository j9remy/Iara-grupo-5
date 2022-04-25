package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Habilidade;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Integer> {

    boolean existsByDescricao(String desc);
    boolean existsByHabilidade(String hab);
    Boolean existsByHabilidadeAndDescricao(String habilidade, String descricao);
}
