package fullStack.template.models;

import fullStack.template.entities.Filiere;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("CHEFFILIERE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChefFiliere extends Professeur{

    @OneToOne(mappedBy = "chefFiliere")
    private Filiere filiere;
}
