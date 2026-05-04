package com.arepacongofio.steamgram.service;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.repository.ReviewJpaRepositpry;
import com.arepacongofio.steamgram.service.abst.AbstractService;
import com.arepacongofio.steamgram.service.interfaces.IReviewService;

@Service
public class ReviewServiceImpl extends AbstractService<Review,Integer> implements IReviewService{

    ReviewJpaRepositpry reviewRepository;

    public ReviewServiceImpl(ReviewJpaRepositpry reviewRepository) {
        super(reviewRepository);
    }
    
}
