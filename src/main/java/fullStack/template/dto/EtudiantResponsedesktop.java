package fullStack.template.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantResponsedesktop {
    private String cne;
    private byte[] profile;
    private String cni;
    private String nom;
    private String prenom;
    private Long id;

}
