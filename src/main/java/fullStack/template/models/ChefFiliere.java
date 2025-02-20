package fullStack.template.models;

import fullStack.template.entities.Filiere;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChefFiliere extends Professeur {

    // Le côté non propriétaire (inverse) de la relation avec Filiere
    @OneToOne
    @JoinColumn(name = "filiere_id")  // La clé étrangère sera dans la table ChefFiliere
    private Filiere filiere;

}
