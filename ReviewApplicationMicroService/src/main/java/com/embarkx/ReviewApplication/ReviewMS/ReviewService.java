package com.embarkx.ReviewApplication.ReviewMS;

import java.util.List;

public interface ReviewService {
	List<Review> getAllReviews(Long companyId);

	void addReview(Long companyId, Review review);

	Review getReview(Long reviewId);

	Review updateReview(Long reviewId, Review review);

	void deleteReview(Long reviewId);
}
