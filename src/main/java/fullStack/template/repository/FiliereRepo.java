package fullStack.template.repository;

import fullStack.template.entities.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepo extends JpaRepository<Filiere,Long> {
    public Filiere findFiliereByNom(String nom);
}
