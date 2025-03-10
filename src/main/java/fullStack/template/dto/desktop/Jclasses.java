package fullStack.template.dto.desktop;

import fullStack.template.entities.Filiere;
import fullStack.template.entities.Matiere;
import fullStack.template.models.Professeur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jclasses {
    private Professeur prof;
    private Matiere matiere;
    private Filiere filiere;
}
