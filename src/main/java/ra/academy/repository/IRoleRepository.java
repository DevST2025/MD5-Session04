package ra.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.academy.entity.Role;
import ra.academy.entity.RoleName;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
