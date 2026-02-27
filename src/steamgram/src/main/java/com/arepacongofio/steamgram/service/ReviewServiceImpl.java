package com.arepacongofio.steamgram.service;

import com.arepacongofio.steamgram.models.Review;
import com.arepacongofio.steamgram.repository.ReviewJpaRepositpry;
import com.arepacongofio.steamgram.service.abst.AbstractService;

public class ReviewServiceImpl extends AbstractService<Review,Integer> {

    ReviewJpaRepositpry repository;

    public ReviewServiceImpl(ReviewJpaRepositpry repository) {
        super(repository);
    }
    
}
