package fullStack.template.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fullStack.template.entities.Notification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("PROFESSEUR")

@Data
@NoArgsConstructor
@AllArgsConstructor

@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // ou JOINED selon ton besoin
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)


public class Professeur extends UserApp{
    private String cni_prof;
    private int apogee;


}
