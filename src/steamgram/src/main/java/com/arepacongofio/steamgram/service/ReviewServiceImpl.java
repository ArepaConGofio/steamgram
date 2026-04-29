package com.arepacongofio.steamgram.service;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.repository.ReviewJpaRepositpry;
import com.arepacongofio.steamgram.service.abst.AbstractService;

@Service
public class ReviewServiceImpl extends AbstractService<Review,Integer> {

    ReviewJpaRepositpry reviewRepository;

    public ReviewServiceImpl(ReviewJpaRepositpry reviewRepository) {
        super(reviewRepository);
    }
    
}
