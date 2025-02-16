package fullStack.template.models;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;


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
}
