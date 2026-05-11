package com.arepacongofio.steamgram.controllers;

import com.arepacongofio.steamgram.domain.requests.ReviewRequest;
import com.arepacongofio.steamgram.domain.responses.ReviewResponse;
import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.mappers.ReviewMapper;
import com.arepacongofio.steamgram.service.interfaces.IReviewService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewControllerTest {

    @Mock
    private IReviewService reviewService;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private ReviewController reviewController;

    private List<Review> reviews;
    private List<ReviewResponse> responses;
    private Review review;
    private ReviewResponse reviewResponse;
    private ReviewRequest request;
    private Integer id;

    @BeforeEach
    void setUp() {
        reviews = List.of(new Review());
        responses = List.of(new ReviewResponse());
        review = new Review();
        reviewResponse = new ReviewResponse();
        request = new ReviewRequest();
        id = 1;
    }

    @Test
    void findAllTest() {
        
        when(reviewService.findAll(any(PageRequest.class))).thenReturn(reviews);
        when(reviewMapper.toResponseList(reviews)).thenReturn(responses);
        
        ResponseEntity<List<ReviewResponse>> response = reviewController.findAll(0, 10);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findByIdNotFoundTest() {
        when(reviewService.findById(id)).thenReturn(null);
        ResponseEntity<ReviewResponse> response = reviewController.findById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void saveTest() {
        
        when(reviewMapper.toEntity(request)).thenReturn(review);
        when(reviewService.save(review)).thenReturn(review);
        when(reviewMapper.toResponse(review)).thenReturn(reviewResponse);
        
        ResponseEntity<ReviewResponse> response = reviewController.save(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteByIdSuccessTest() {
        when(reviewService.deleteById(id)).thenReturn(true);
        ResponseEntity<Void> response = reviewController.deleteById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
