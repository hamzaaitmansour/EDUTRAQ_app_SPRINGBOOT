package fullStack.template.repository;

import fullStack.template.entities.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereRepo extends JpaRepository<Matiere,Long> {
    public Matiere findMatiereByNom(String nom);
}
