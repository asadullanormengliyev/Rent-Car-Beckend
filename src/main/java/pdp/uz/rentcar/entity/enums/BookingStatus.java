package pdp.uz.rentcar.entity.enums;

public enum BookingStatus {
    PENDING,     // Buyurtma yaratilgan, hali tasdiqlanmagan
    CONFIRMED,   // Buyurtma tasdiqlandi
    CANCELLED,   // Buyurtma bekor qilindi
    COMPLETED,   // Buyurtma tugallandi (mashina qaytarildi)
    REJECTED     // Buyurtma rad etildi
}
