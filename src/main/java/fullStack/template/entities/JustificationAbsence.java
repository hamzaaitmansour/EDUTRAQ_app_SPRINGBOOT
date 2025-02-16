package fullStack.template.entities;

import fullStack.template.models.UserApp;
import jakarta.persistence.*;

@Entity
public class JustificationAbsence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String document;
    private String description;

    @ManyToOne
    private UserApp userApp;
    @OneToOne
    private Seance seance;
}