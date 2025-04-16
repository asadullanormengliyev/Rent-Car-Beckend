🚗 Rent Car – REST API
This is a role-based Car Rental REST API built with Spring Boot, Spring Security, and PostgreSQL. The system is designed to manage cars, handle bookings, process reviews, and ensure secure access for both admin and user roles.

✅ Key Features
🔐 JWT-based Authentication & Authorization
Secure login system with role-based access control (Admin & User).

🚘 Car Management
Admins can add, update, and delete cars. Each car includes category, status, image, location, and daily rental price.

📍 Location Handling
Cars are linked to pickup and drop-off locations for better route management.

📅 Booking System with Conflict Validation
Users can book available cars by selecting start and end dates. The system checks for overlapping bookings and calculates the total rental price based on the number of days and car’s daily rate.

📝 Review System
Only users who have booked a car can leave a review, ensuring trust and authenticity.

🧾 Data Persistence with JPA
All entities such as Car, Booking, Review, User, and Location are managed through Spring Data JPA and stored in a PostgreSQL database.

This backend API is structured for clean code, maintainability, and scalability, making it a solid base for any production-ready rental platform.
