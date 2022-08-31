package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
