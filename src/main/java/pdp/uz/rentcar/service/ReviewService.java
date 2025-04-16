package pdp.uz.rentcar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.uz.rentcar.dtos.review.request.ReviewCreateRequest;
import pdp.uz.rentcar.dtos.review.response.ReviewCreateResponse;
import pdp.uz.rentcar.entity.Car;
import pdp.uz.rentcar.entity.Review;
import pdp.uz.rentcar.entity.User;
import pdp.uz.rentcar.exception.RecordNotFoundException;
import pdp.uz.rentcar.repository.BookingRepository;
import pdp.uz.rentcar.repository.CarRepository;
import pdp.uz.rentcar.repository.ReviewRepository;
import pdp.uz.rentcar.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public ReviewCreateResponse createReview(ReviewCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        boolean booking = bookingRepository.existsBookingByUser_IdAndCar_Id(user.getId(), car.getId());
        if (!booking){
            throw new RecordNotFoundException("User and Car not found");
        }

        Review review = new Review();
        review.setUser(user);
        review.setCar(car);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setCreated(LocalDateTime.now());
        Review saved = reviewRepository.save(review);
        return convertToDto(saved);
    }

    public List<ReviewCreateResponse> getAllReviews() {

        List<Review> reviews = reviewRepository.findAll();

        if (reviews.isEmpty()) {
            return new ArrayList<>();
        }

        return reviews.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ReviewCreateResponse> getByCarId(UUID carId) {
        
        List<Review> reviews = reviewRepository.findAllByCarId(carId);

        if (!carRepository.existsById(carId))
            throw new IllegalArgumentException("This Car ID does not exist: " + carId);

        if (reviews.isEmpty())
            throw new RuntimeException("There are no reviews for this car.");

        return reviews.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ReviewCreateResponse> getByUserId(UUID userId) {

        List<Review> reviews = reviewRepository.findAllByUserId(userId);

        if (!userRepository.existsById(userId))
            throw new IllegalArgumentException("This user does not exist: " + userId);

        if (reviews.isEmpty())
            throw new RuntimeException("This user has not written any reviews");

        return reviews.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void delete(UUID reviewId) {

        if (!reviewRepository.existsById(reviewId))
            throw new IllegalArgumentException("Review not found with " + reviewId + "ID");

        reviewRepository.deleteById(reviewId);
    }

    private ReviewCreateResponse convertToDto(Review review) {
        return ReviewCreateResponse.builder()
                .id(review.getId())
                .userId(review.getUser().getId())
                .carId(review.getCar().getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .created(review.getCreated())
                .build();
    }

}
