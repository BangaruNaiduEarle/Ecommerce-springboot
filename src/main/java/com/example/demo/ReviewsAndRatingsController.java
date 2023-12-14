package com.example.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.ReviewsAndRatingsModel;
import com.example.demo.service.ReviewsAndRatingsService;

@RestController
@RequestMapping
public class ReviewsAndRatingsController {
	@Autowired
	private ReviewsAndRatingsService reviewsAndRatingsService; 
	
	@GetMapping("/reviewsAndRatings/getReviewsByProductId/{productId}")
    public ResponseEntity<List<ReviewsAndRatingsModel>> getReviewsByProductId(@PathVariable Long productId) {
        List<ReviewsAndRatingsModel> reviews = reviewsAndRatingsService.getReviewsByProductId(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
	
	@GetMapping("/reviewsAndRatings/overall-rating/{productId}")
	public ResponseEntity<Double> getOverallRating(@PathVariable Long productId) {
	    double overallRating = reviewsAndRatingsService.calculateOverallRating(productId);
	    return new ResponseEntity<>(overallRating, HttpStatus.OK);
	}
	
	@PostMapping("/reviewsAndRatings/addReview")
    public ResponseEntity<ReviewsAndRatingsService.ReviewsAndRatingsModelResponse> addReview(
            @RequestParam Long productId,
            @RequestParam Long userId,
            @RequestParam String text,
            @RequestParam int rating,
            @RequestParam(required = false) MultipartFile reviewImage,
            @RequestParam(required = false) MultipartFile reviewVideo) {
		 String imageUrl = reviewImage != null ? uploadImage(reviewImage) : null;
	        String videoUrl = reviewVideo != null ? uploadVideo(reviewVideo) : null;

	       // Review newReview = reviewService.addReview(productId, userId, text, rating, imageUrl, videoUrl);
	       // return new ResponseEntity<>(newReview, HttpStatus.CREATED);

	        ReviewsAndRatingsService.ReviewsAndRatingsModelResponse newReview = reviewsAndRatingsService.addReview(productId, userId, text, rating, imageUrl, videoUrl);
        //return new ResponseEntity<>(newReview, HttpStatus.CREATED);
	        if (newReview.getReviewsAndRatings() != null) {
	            return new ResponseEntity<>(newReview, HttpStatus.CREATED);
	        } else {
	            return new ResponseEntity<>(newReview, HttpStatus.BAD_REQUEST);
	        }
    }
	public static String uploadImage(MultipartFile reviewImage) {
        return uploadFile(reviewImage, "images");
    }

    public static String uploadVideo(MultipartFile reviewVideo) {
        return uploadFile(reviewVideo, "videos");
    }

    private static String uploadFile(MultipartFile file, String subDirectory) {
        try {
            // Create the directory if it doesn't exist
            File directory = new File("uploads/" + subDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Generate a unique file name
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uniqueFileName = System.currentTimeMillis() + "-" + fileName;

            // Save the file to the server
            String filePath = directory.getAbsolutePath() + File.separator + uniqueFileName;
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                fileOutputStream.write(file.getBytes());
            }

            return "/uploads/" + subDirectory + "/" + uniqueFileName; // Return the URL/path
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file");
        }
        finally {
            // Perform cleanup (if needed)
        }
    }

}
