package fullStack.template.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Image {

    private Long id;
    private String profile; // Image en Base64
    private String email;
    private String password;
    private String cne;
    private String cni;
    private String lastname;
    private String firstname;
    private String telephone;
    private boolean finger;


}
