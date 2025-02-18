package fullStack.template.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fullStack.template.entities.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("ETUDIANT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant extends UserApp {

    private String empreinte;
    private String cne;
    private String cni_etudiant;

    @ManyToOne
    private Filiere filiere;

    @JsonIgnore
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Presence> presences;


    @JsonIgnore
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks;

    @JsonIgnore
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JustificationAbsence> justifications;

    @ManyToMany
    @JoinTable(
            name = "etudiant_seance",
            joinColumns = @JoinColumn(name = "etudiant_id"),
            inverseJoinColumns = @JoinColumn(name = "seance_id")
    )
    private List<Seance> seances;


}
