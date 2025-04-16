package pdp.uz.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.rentcar.entity.Role;
import pdp.uz.rentcar.entity.enums.UserRole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoles(UserRole role);
}
