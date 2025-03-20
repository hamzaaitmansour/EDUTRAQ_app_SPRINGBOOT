package fullStack.template.dto.desktop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileChef {

    private Long id;
    private String firstname;
    private String lastname;
    private String telephone;
    private String cni;
    private String password;
}
