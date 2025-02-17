package fullStack.template.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fullStack.template.models.Etudiant;
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
    private int week;
    private LocalDate date;

    @ManyToOne
    private Seance seance;
    @ManyToOne
    private Etudiant etudiant;
    @ManyToOne
    private  Archive archive;

    @JsonIgnore
    @OneToOne(mappedBy = "presence")
    private JustificationAbsence justificationAbsence;

    @JsonIgnore
    @OneToOne(mappedBy = "presence")
    private Feedback feedback;

}
