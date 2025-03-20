package fullStack.template.dto.desktop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresenceDeskRequest {
    private Long id;
    private boolean present;
}
