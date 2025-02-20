package fullStack.template.entities;

import fullStack.template.models.Etudiant;
import fullStack.template.models.UserApp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JustificationAbsence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] document;
    // Stocke le nom du fichier (optionnel)
    private String fileName;

    // Stocke le type MIME de l'image (ex: image/jpeg, image/png)
    private String contentType;
    private String description;

    @ManyToOne
    private Etudiant etudiant;

    @OneToOne
    private Presence presence;
}
