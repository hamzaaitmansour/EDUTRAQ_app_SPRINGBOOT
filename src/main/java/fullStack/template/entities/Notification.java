package fullStack.template.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fullStack.template.models.UserApp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private boolean vu;

    @JsonIgnore
    @ManyToOne
    private UserApp userApp;
}
