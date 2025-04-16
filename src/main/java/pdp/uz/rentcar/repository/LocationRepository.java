package pdp.uz.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.rentcar.entity.Location;

import java.util.UUID;
@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
}
