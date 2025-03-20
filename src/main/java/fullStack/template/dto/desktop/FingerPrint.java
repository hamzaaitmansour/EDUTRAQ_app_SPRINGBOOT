package fullStack.template.dto.desktop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Fallback;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FingerPrint {
    private Long id;
    private String empreinte;
}
