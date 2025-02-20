package fullStack.template.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fullStack.template.models.Etudiant;
import fullStack.template.models.Professeur;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "l'heure de la seance est vide")
    private String heure;

    @NotBlank(message = "le jour de la seance est vide")
    private String jour;

    @NotBlank(message = "le type de la seance est vide")
    private String type;

    @JsonIgnore
    @ManyToOne
    private Filiere filiere;

    @JsonIgnore
    @ManyToOne
    private Matiere matiere;

    @JsonIgnore
    @OneToMany(mappedBy = "seance")
    private List<Presence> presences;

    @ManyToMany(mappedBy = "seances")
    private List<Etudiant> etudiants;

    @ManyToOne
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "professeur_id") // Nom de la colonne dans la table Seance
    private Professeur professeur;

}
