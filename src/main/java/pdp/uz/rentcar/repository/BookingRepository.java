package pdp.uz.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdp.uz.rentcar.entity.Booking;

import java.util.UUID;
@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    boolean existsBookingByUser_IdAndCar_Id(UUID userId, UUID carId);
}
