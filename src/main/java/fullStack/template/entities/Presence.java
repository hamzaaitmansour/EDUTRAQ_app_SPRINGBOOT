package fullStack.template.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fullStack.template.models.Etudiant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String statut;
    private String remarque;
    private int week;
    private int year;
    private LocalDate date;
    @JsonIgnore
    @ManyToOne
    private Seance seance;

    @JsonIgnore
    @ManyToOne
    private Etudiant etudiant;



    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "justification_id")
    private JustificationAbsence justificationAbsence;

    @JsonIgnore
    @OneToMany(mappedBy = "presence", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks;
}
