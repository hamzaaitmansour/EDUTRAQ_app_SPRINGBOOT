package fullStack.template.repository;

import fullStack.template.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalleRepo extends JpaRepository<Salle,Long> {
    public Salle findSalleByNom(String nom);
}
