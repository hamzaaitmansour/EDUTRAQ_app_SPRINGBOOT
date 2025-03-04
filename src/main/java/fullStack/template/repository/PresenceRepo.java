package fullStack.template.repository;

import fullStack.template.entities.Presence;
import fullStack.template.entities.Seance;
import fullStack.template.models.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenceRepo extends JpaRepository<Presence,Long> {
    public List<Presence> getPresencesByWeekAndEtudiantAndYear(int week, Etudiant etudiant,int year);
    @Query(value = "SELECT (COUNT(CASE WHEN statut = 'absent' THEN 1 END) * 100.0 / COUNT(*)) " +
            "FROM presence WHERE seance_id = :seanceId", nativeQuery = true)
    Double getAbsencePercentageBySeance(@Param("seanceId") Long seanceId);


}
