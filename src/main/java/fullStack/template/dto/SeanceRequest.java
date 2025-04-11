package fullStack.template.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeanceRequest {

    private Long id_matiere;
    private Long id_user;
    private Long id_salle;
    private String jour;
    private String heure;
    private String type;
}
