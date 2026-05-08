package com.arepacongofio.steamgram.service;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.repository.ReviewJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;
import com.arepacongofio.steamgram.service.interfaces.IReviewService;

@Service
public class ReviewServiceImpl extends AbstractService<Review,Integer> implements IReviewService{

    ReviewJpaRepository reviewRepository;

    public ReviewServiceImpl(ReviewJpaRepository reviewRepository) {
        super(reviewRepository);
    }
    
}
