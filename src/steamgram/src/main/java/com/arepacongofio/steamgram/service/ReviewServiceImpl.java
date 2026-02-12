package com.arepacongofio.steamgram.service;

import java.util.List;

import com.arepacongofio.steamgram.models.Review;
import com.arepacongofio.steamgram.repository.ReviewJpaRepositpry;
import com.arepacongofio.steamgram.service.interfaces.IService;

public class ReviewServiceImpl implements IService<Review,Integer> {

    ReviewJpaRepositpry repository;

    

    public ReviewServiceImpl(ReviewJpaRepositpry repository) {
        this.repository = repository;
    }

    @Override
    public List<Review> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public Review findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Review save(Review entity) {
        return repository.save(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        if(!existsById(id)){
            return false;
        }
        Review deleteReview = findById(id);
        repository.delete(deleteReview);
        return true;
    }
    
}
