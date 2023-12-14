package ra.academy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    @NotBlank(message = "Not blank")
    @Size(min = 6, max = 50, message = "Độ dài nằm trong khoảng 6 -> 50")
    private String username;
    @NotBlank(message = "Not blank")
    @Pattern(regexp = "^(?=.* \\d )(?=.*[az])(?=.*[AZ])(?=.*[@#$%]) .{6,15}", message = "Mật khẩu có ít nhất một chữ số, một chữ cái viết hoa, một chữ cái viết thường và một ký hiệu đặc biệt (“@#")
    private String password;
    private String confirmPassword;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @NotBlank(message = "Not blank")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    @NotBlank(message = "Not blank")
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b")
    private String phone;
    private MultipartFile file;
    private Boolean status;
    private Set<String> listRoles;
}
