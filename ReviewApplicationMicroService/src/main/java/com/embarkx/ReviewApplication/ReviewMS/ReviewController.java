package com.embarkx.ReviewApplication.ReviewMS;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}

	@GetMapping
	public ResponseEntity<?> getAllReviews(@RequestParam Long companyId) {
		try {
			List<Review> reviews = reviewService.getAllReviews(companyId);
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<?> getReview(@PathVariable Long reviewId) {
		try {
			Review review = reviewService.getReview(reviewId);
			return new ResponseEntity<>(review, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
	}

	@PutMapping("/{reviewId}")
	public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
		try {
			Review updatedReview = reviewService.updateReview(reviewId, review);
			return new ResponseEntity<>(updatedReview, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
		try {
			reviewService.deleteReview(reviewId);
			return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
	}

	@PostMapping
	public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review) {
		try {
			reviewService.addReview(companyId, review);
			return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
	}
}
