package fullStack.template.dto.desktop;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JustResponseD {

    private String matiere;
    private String etudiant;

    private String filiere;
    private String date;

    @Lob
    private byte[] document;
    private Long id;
    private String description;
}
