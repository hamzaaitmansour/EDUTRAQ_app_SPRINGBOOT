package fullStack.template.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresenceResponse {
    private Long id;
    private String statut;
    private String heure;
    private String jour;
    private String type;
    private String matiere_nom;
    private String prof_nom;
    private LocalDate date;

}
