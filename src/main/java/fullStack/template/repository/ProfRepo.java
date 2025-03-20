package fullStack.template.repository;

import fullStack.template.models.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface ProfRepo extends JpaRepository<Professeur,Long> {

    public Professeur findProfesseurByEmail(String email);
    public List<Professeur> findAllByRole(String role);
}
