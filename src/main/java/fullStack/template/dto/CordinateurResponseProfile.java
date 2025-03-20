package fullStack.template.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CordinateurResponseProfile {
    private String firstname;
    private String lastname;
    private String email;
    private String telephone;
    private String password;
}
