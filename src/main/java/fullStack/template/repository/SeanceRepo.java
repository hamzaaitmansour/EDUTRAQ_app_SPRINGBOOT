package fullStack.template.repository;

import fullStack.template.entities.Filiere;
import fullStack.template.entities.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeanceRepo extends JpaRepository<Seance,Long> {
  public List<Seance> findSeancesByHeureAndJour(String heure, String jour);
}
