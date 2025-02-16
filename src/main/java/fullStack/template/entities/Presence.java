package fullStack.template.entities;

import fullStack.template.models.UserApp;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String statut;
    private String remarque;
    private LocalDate date;

    @ManyToOne
    private Seance seance;
    @ManyToOne
    private UserApp userApp;

}
