package fullStack.template.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fullStack.template.entities.Filiere;
import fullStack.template.entities.Presence;
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
    private String cni;
    @ManyToOne
    private Filiere filiere_etudiant;
    @JsonIgnore
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Presence> presences;
}
