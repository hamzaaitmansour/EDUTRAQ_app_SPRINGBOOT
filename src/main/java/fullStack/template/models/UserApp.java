package fullStack.template.models;

import fullStack.template.entities.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Inheritance(strategy = InheritanceType.JOINED) // Utiliser JOINED pour des tables séparées
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;
    @NotBlank(message = "Le champ email est vide")
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Le champ password est vide")
    private String password;
    @Pattern(regexp = "^(?:\\+212|0)(5|6|7)\\d{8}$",message = "ce numero de telephone est invalid")
    private String telephone;
    @Size(min = 4 , message = "le champ Role est inferieur a 4 caracteres")
    private String role; // "COORDINATEUR", "PROFESSEUR", "ETUDIANT","CHEF"



    @JsonIgnore
    @OneToMany(mappedBy = "userApp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;









}
