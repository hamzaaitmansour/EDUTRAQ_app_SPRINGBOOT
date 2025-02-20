package fullStack.template.repository;

import fullStack.template.entities.JustificationAbsence;
import fullStack.template.service.JustificationService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JustificationRepo extends JpaRepository<JustificationAbsence,Long> {
}
