package fullStack.template.repository;

import fullStack.template.entities.Filiere;
import fullStack.template.entities.Matiere;
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
            "FROM presence WHERE seance_id = :seanceId AND etudiant_id = :etudiantId",
            nativeQuery = true)
    Double getAbsencePercentageBySeance(@Param("seanceId") Long seanceId,
                                        @Param("etudiantId") Long etudiantId);



    @Query("SELECT p FROM Presence p WHERE p.seance.filiere = :filiere AND p.seance.matiere = :matiere AND p.seance.type = :type")
    List<Presence> findAllByFiliereAndMatiereAndType(@Param("filiere") Filiere f,
                                                     @Param("matiere") Matiere m,
                                                     @Param("type") String type);

}
