package fullStack.template.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fullStack.template.models.UserApp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int effectif;
    @JsonIgnore
    @OneToOne
    private UserApp userApp;
    @OneToMany(mappedBy = "filiere")
    private List<Seance> seances;
}