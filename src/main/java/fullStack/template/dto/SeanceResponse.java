package fullStack.template.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeanceResponse {

    private String heure;
    private String jour;
    private String type;
    private String matiere_nom;
    private String prof_nom;

}
