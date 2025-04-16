package pdp.uz.rentcar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pdp.uz.rentcar.entity.enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Car car;
    private double totalPrice;
    @ManyToMany
    private List<Location> pickupLocation;
    @ManyToMany
    private List<Location> dropOffLocation;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime created;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
