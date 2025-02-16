package fullStack.template.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ErrorShared {
    String message;
    Date timestamp;
    int code;
}
