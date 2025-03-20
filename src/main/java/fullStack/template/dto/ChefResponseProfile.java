package fullStack.template.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChefResponseProfile {
    private Long id;
    private String email;
    private String password;
    private String lastname;
    private String firstname;
    private String telephone;
    private String filiere;
    private String cni;
    private String apogee;

}
