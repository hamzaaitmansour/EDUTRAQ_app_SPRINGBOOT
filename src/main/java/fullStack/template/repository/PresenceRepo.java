package fullStack.template.repository;

import fullStack.template.entities.Presence;
import fullStack.template.models.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenceRepo extends JpaRepository<Presence,Long> {
    public List<Presence> getPresencesByWeekAndEtudiantAndYear(int week, Etudiant etudiant,int year);

}
