package fullStack.template.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fullStack.template.models.ChefFiliere;
import fullStack.template.models.Etudiant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private int effectif;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "chef_filiere_id") // Filiere est le propriétaire de la relation
    private ChefFiliere chefFiliere;

    @JsonIgnore
    @OneToMany(mappedBy = "filiere")
    private List<Seance> seances;

    @JsonIgnore
    @OneToMany(mappedBy = "filiere")
    private List<Etudiant> etudiants;
}
