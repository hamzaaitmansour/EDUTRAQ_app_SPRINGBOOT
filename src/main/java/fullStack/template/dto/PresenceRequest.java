package fullStack.template.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresenceRequest {
    private Long id_seance;
    private Long id_etudiant;
    private String statut;
    private String remarque;
    private LocalDate date;

}
