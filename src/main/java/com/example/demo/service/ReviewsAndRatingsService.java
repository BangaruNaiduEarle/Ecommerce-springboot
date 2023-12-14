package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ProductModel;
import com.example.demo.model.ReviewsAndRatingsModel;
import com.example.demo.model.UserModel;
import com.example.demo.repo.ReviewsAndRatingsRepo;

@Service
public class ReviewsAndRatingsService {
	@Autowired
    private ReviewsAndRatingsRepo reviewsAndRatingsRepo;

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    public class ReviewsAndRatingsModelResponse {
	    private ReviewsAndRatingsModel reviewsAndRatings;
	    private String message;
		public ReviewsAndRatingsModel getReviewsAndRatings() {
			return reviewsAndRatings;
		}
		public void setReviewsAndRatings(ReviewsAndRatingsModel reviewsAndRatings) {
			this.reviewsAndRatings = reviewsAndRatings;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
	}
    public List<ReviewsAndRatingsModel> getReviewsByProductId(Long productId) {
        return reviewsAndRatingsRepo.findByProductProductId(productId);
    }

    public ReviewsAndRatingsModelResponse addReview(Long productId, Long userId, String text, int rating, String reviewImage, String reviewVideo) {
        ProductModel product = productService.getProductById(productId);

        if (product == null) {
        	ReviewsAndRatingsModelResponse response = new ReviewsAndRatingsModelResponse();

	        response.setMessage("Product Not Found");
	        return response;
        }

        UserModel user = userService.getUserById(userId);

        if (user == null) {
        	ReviewsAndRatingsModelResponse response = new ReviewsAndRatingsModelResponse();

	        response.setMessage("User Not Found");
	        return response;

        }

        ReviewsAndRatingsModel review = new ReviewsAndRatingsModel();
        review.setProduct(product);
        review.setUser(user);
        review.setText(text);
        review.setRating(rating);
        //review.setReviewImage(reviewImage);
        //review.setReviewVideo(reviewVideo);
        try {
            if (reviewImage != null) {
                review.setReviewImage(reviewImage.getBytes());
            }
            if (reviewVideo != null) {
                review.setReviewVideo(reviewVideo.getBytes());
            }
        } catch (Exception ex) {
        	ReviewsAndRatingsModelResponse response = new ReviewsAndRatingsModelResponse();

	        response.setMessage("Error Uplaoding Image or Video");
	        return response;
        }
        ReviewsAndRatingsModel savedReviewAndRating = reviewsAndRatingsRepo.save(review);
        
        // If everything is successful
        ReviewsAndRatingsModelResponse response = new ReviewsAndRatingsModelResponse();
        response.setReviewsAndRatings(savedReviewAndRating);
        return response;
        // Save the review entity to the database
    }
   
    public double calculateOverallRating(Long productId) {
        List<ReviewsAndRatingsModel> reviews = reviewsAndRatingsRepo.findByProductProductId(productId);

        if (reviews.isEmpty()) {
            return 0.0; // or any default value
        }

        double sum = reviews.stream().mapToDouble(ReviewsAndRatingsModel::getRating).sum();
        return sum / reviews.size();
    }


}
