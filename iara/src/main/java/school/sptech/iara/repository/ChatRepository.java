package school.sptech.iara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.iara.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    Chat findByServicoAtribuido_Id(Integer id);
}
