package com.capg.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import com.capg.rest.dto.ProductDto;
import com.capg.rest.entity.Product;
import com.capg.rest.entity.Reviews;
import com.capg.rest.exception.ReviewNotFoundException;
import com.capg.rest.repository.ReviewsRepository;

@Service
public class ReviewService {
	@Autowired
	ReviewsRepository reviewsRepository;

	@Autowired
	RestTemplate restTemplate;

	@Transactional(readOnly = true)
	public List<Reviews> getAllReviews() {
		return reviewsRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Reviews getReviewById(int reviewId) throws ReviewNotFoundException {
		Optional<Reviews> oprev = reviewsRepository.findById(reviewId);
		if (oprev.isPresent())
			return oprev.get();
		throw new ReviewNotFoundException("Review doesnot exist with Id" + reviewId);
	}

	@Transactional(readOnly = true)
	public Reviews getByProductId(int productId) throws ReviewNotFoundException {
		Optional<Reviews> op = reviewsRepository.findByProductId(productId);
		if (op.isPresent()) {
			Product pd = restTemplate
					.getForEntity("http://localhost:8090/product/{productId}", Product.class, productId).getBody();
			Reviews r = op.get();
			r.setProductDto(new com.capg.rest.dto.ProductDto());
			r.getProductDto().setProductId(productId);
			r.getProductDto().setProductName(pd.getProductName());
			r.getProductDto().setProductDescription(pd.getProductDescription());
			r.getProductDto().setProductPrice(pd.getProductPrice());
			r.getProductDto().setProductQuantity(pd.getProductQuantity());

			return r;
		}
		throw new ReviewNotFoundException("Reviews does not exist with product id " + productId);
	}
}
