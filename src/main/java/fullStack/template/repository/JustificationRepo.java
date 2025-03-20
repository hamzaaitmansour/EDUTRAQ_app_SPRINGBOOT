package fullStack.template.repository;

import fullStack.template.dto.desktop.Jclasses;
import fullStack.template.entities.Filiere;
import fullStack.template.entities.JustificationAbsence;
import fullStack.template.entities.Matiere;
import fullStack.template.models.Professeur;
import fullStack.template.service.JustificationService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
@Repository
public interface JustificationRepo extends JpaRepository<JustificationAbsence, Long> {
    @Query("SELECT j FROM JustificationAbsence j WHERE j.presence.seance.professeur = :prof ORDER BY j.date DESC")
    JustificationAbsence findTopByProfesseurOrderByDateJustificationDesc(@Param("prof") Professeur prof);


     @Query("SELECT j FROM JustificationAbsence j WHERE j.presence.seance.professeur = :prof AND j.presence.seance.matiere = :matiere AND j.presence.seance.filiere = :filiere")
        List<JustificationAbsence> findAllByProfesseurAndMatiereAndFiliere(
                @Param("prof") Professeur prof,
                @Param("matiere") Matiere matiere,
                @Param("filiere") Filiere filiere
        );
    @Query("SELECT j FROM JustificationAbsence j WHERE j.presence.seance.professeur = :prof ORDER BY j.id DESC")
    JustificationAbsence findLatestByProfesseur(@Param("prof") Professeur prof);


    @Query("SELECT j FROM JustificationAbsence j WHERE j.presence.seance.filiere = :filiere")
    List<JustificationAbsence> findByFiliere(@Param("filiere")Filiere filiere);
}
