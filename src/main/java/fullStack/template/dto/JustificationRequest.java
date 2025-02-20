package fullStack.template.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JustificationRequest {
    private String description;
    private Long presence_id;
    private Long etudiant_id;
    private MultipartFile file;
}
