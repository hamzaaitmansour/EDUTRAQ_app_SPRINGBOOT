package fullStack.template.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fullStack.template.models.UserApp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private String type;
    private LocalDate date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_app_id", referencedColumnName = "id", nullable = false)

    private UserApp userApp;

}
