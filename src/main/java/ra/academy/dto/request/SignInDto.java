package ra.academy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {
    @NotBlank(message = "Not blank")
    private String username;
    @NotBlank(message = "Not blank")
    private String password;
}
