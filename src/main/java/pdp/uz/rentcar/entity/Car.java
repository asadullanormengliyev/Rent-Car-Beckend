package pdp.uz.rentcar.entity;

import jakarta.persistence.*;
import lombok.*;
import pdp.uz.rentcar.entity.enums.CarStatus;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "car")
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String model;
    private String carNumber;
    private String color;
    private String transmission; // automat yoki mexanik
    private String mileage; // Bosib otgan yo'li
    private int seats; // o'rindiqlar soni
    private String year;
    private double pricePerDay;
    @OneToOne
    private CarAttachment attachment;
    @Enumerated(EnumType.STRING)
    private CarStatus status;
    @ManyToOne
    private CarCategory carCategory;
    @ManyToOne
    private Location location;
}
