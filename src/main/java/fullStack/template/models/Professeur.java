package fullStack.template.models;

import fullStack.template.entities.Filiere;
import fullStack.template.entities.Seance;
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
public class Professeur extends UserApp {
    private String cni_prof;
    private String apogee;


    @OneToOne
    @JoinColumn(name = "filiere_id", nullable = true)  // Le champ filiere_id peut être null
    private Filiere filiere;

    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seance> seances;
}
