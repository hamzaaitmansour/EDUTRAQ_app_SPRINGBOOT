package fullStack.template.entities;

import fullStack.template.models.UserApp;
import jakarta.persistence.*;

@Entity
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatutPresence statut;
    private String remarque;

    @ManyToOne
    private Seance seance;
    @ManyToOne
    private UserApp userApp;

}

enum StatutPresence {
    PRESENT, ABSENT
}