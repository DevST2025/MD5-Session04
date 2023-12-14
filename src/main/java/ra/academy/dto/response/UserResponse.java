package ra.academy.dto.response;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import ra.academy.entity.Role;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String username;
    @Temporal(TemporalType.DATE)
    private Date birthDay;
    private String email;
    private String phone;
    private String avatar;
    private Boolean status;
    private Set<Role> roles;
}
