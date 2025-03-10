package fullStack.template.dto.desktop;

import fullStack.template.entities.Presence;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresenceDResponse {

    private Long etudiant_id;
    private String etudiant_nom;

    private String etudiant_cne;
    private List<Presence> presences;

}
