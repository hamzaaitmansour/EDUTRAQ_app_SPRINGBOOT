package fullStack.template.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fullStack.template.entities.Feedback;
import fullStack.template.entities.JustificationAbsence;
import fullStack.template.entities.Presence;
import fullStack.template.entities.Seance;
import fullStack.template.entities.Filiere;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant extends UserApp {
    @Lob
    private byte[] empreinte;

    private String cne;
    private String cni_etudiant;
    @Lob
    private byte[] profile;

    @ManyToOne
    private Filiere filiere;

    @JsonIgnore
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Presence> presences;



    @JsonIgnore
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JustificationAbsence> justifications;

//    @ManyToMany
//    @JoinTable(
//            name = "etudiant_seance",
//            joinColumns = @JoinColumn(name = "etudiant_id"),
//            inverseJoinColumns = @JoinColumn(name = "seance_id")
//    )
////    private List<Seance> seances;
}
