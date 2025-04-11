package fullStack.template.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEtudiant {

    private String lastname;
    private String firstname;
    private String cne;
    private String cni;
    private String telephone;
}
