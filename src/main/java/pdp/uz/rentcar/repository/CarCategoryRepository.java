package pdp.uz.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.rentcar.entity.CarCategory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarCategoryRepository extends JpaRepository<CarCategory, UUID> {
    Optional<CarCategory> findById(UUID id);
}