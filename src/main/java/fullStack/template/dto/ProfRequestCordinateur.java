package fullStack.template.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfRequestCordinateur {

    @NotBlank
    private String apogee;

    private  Long id;
    @NotBlank
    @Email(message = "L'email doit être valide.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "L'email doit se terminer par @gmail.com")
    private String email;

    private String nom;

}
