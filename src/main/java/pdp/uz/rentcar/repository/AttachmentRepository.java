package pdp.uz.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.rentcar.entity.CarAttachment;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface AttachmentRepository extends JpaRepository<CarAttachment, UUID> {

}
