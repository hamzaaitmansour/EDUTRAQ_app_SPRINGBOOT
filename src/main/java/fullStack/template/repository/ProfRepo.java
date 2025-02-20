package fullStack.template.repository;

import fullStack.template.models.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfRepo extends JpaRepository<Professeur,Long> {

    public Professeur findProfesseurByEmail(String email);
}
