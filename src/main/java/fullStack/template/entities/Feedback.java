package fullStack.template.entities;

import fullStack.template.models.UserApp;
import jakarta.persistence.*;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commentaire;

    @ManyToOne
    private UserApp userApp;

    @OneToOne
    private Seance seance;
}