package fullStack.template.dto.desktop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresenceHistRequest {
    private Long filiere_id;
    private Long matiere_id;
    private String type;

}
