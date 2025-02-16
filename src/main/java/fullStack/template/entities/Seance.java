package fullStack.template.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fullStack.template.models.UserApp;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
    @JsonIgnore
    @ManyToOne
    private UserApp userApp;
    @JsonIgnore
    @OneToOne(mappedBy = "seance")

    private JustificationAbsence justificationAbsence;
    @JsonIgnore
    @OneToOne(mappedBy = "seance")
    private Feedback feedback;



    @ManyToOne
    private Salle salle;
}
