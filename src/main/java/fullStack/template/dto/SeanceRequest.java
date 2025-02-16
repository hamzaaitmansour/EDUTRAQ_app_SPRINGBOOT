package fullStack.template.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeanceRequest {

    private String heure;
    private String jour;
    private String type;
    private Long filiere_id;
    private String matiere_nom;
    private int prof_id;
    private String salle_nom;
}
