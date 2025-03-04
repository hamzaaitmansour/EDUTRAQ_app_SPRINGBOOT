package fullStack.template.entities;

import fullStack.template.models.Etudiant;
import fullStack.template.models.UserApp;
import jakarta.annotation.Nullable;
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

    private String description;

    @ManyToOne
    private Etudiant etudiant;

    @OneToOne
    private Presence presence;
}
