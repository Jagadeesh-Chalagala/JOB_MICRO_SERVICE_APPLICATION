package com.embarkx.ReviewApplication.ReviewMS;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepository;

	@Override
	public List<Review> getAllReviews(Long companyId) {

		return reviewRepository.findByCompanyId(companyId);
	}

	@Override
	public void addReview(Long companyId, Review review) {

		review.setCompanyId(companyId);
		reviewRepository.save(review);

	}

	@Override
	public Review getReview(Long reviewId) {

		Optional<Review> optOldReview = reviewRepository.findById(reviewId);
		if (optOldReview.isPresent())
			return optOldReview.get();
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review with id: " + reviewId + " is not found");

	}

	@Override
	public Review updateReview(Long reviewId, Review review) {

		Optional<Review> optOldReview = reviewRepository.findById(reviewId);
		if (optOldReview.isPresent()) {
			Review oldReview = optOldReview.get();
			oldReview.setDescription(review.getDescription());
			oldReview.setRating(review.getRating());
			oldReview.setTitle(review.getTitle());
			oldReview.setCompanyId(review.getCompanyId());
			reviewRepository.save(oldReview);
			return oldReview;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review with id: " + reviewId + " is not found");

	}

	@Override
	public void deleteReview(Long reviewId) {

		Optional<Review> optOldReview = reviewRepository.findById(reviewId);
		if (optOldReview.isPresent()) {
			reviewRepository.deleteById(reviewId);
			return;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review with id: " + reviewId + " is not found");

	}

}
