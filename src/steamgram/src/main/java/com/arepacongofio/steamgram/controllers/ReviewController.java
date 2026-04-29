package com.arepacongofio.steamgram.controllers;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arepacongofio.steamgram.controllers.interfaces.IController;
import com.arepacongofio.steamgram.domain.requests.ReviewRequest;
import com.arepacongofio.steamgram.domain.responses.ReviewResponse;
import com.arepacongofio.steamgram.mappers.ReviewMapper;
import com.arepacongofio.steamgram.service.interfaces.IReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/review")
@Tag(name = "Review", description = "Complete review management")
public class ReviewController implements IController<ReviewResponse, ReviewRequest, Integer> {

    private final IReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public ReviewController(IReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    @Override
    @GetMapping
    @Operation(summary = "List reviews", description = "Lists all reviews")
    public ResponseEntity<List<ReviewResponse>> findAll(@RequestParam int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(reviewMapper.toResponseList(reviewService.findAll(PageRequest.of(page, pageSize))));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Find a Review by its Id", description = "Find a Review by its Id")
    public ResponseEntity<ReviewResponse> findById(@Valid @PathVariable Integer id) {
        com.arepacongofio.steamgram.entities.Review review = reviewService.findById(id);
        if (review == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewMapper.toResponse(review));
    }

    @Override
    @PostMapping
    @Operation(summary = "Save a Review", description = "Save a Review")
    public ResponseEntity<ReviewResponse> save(@Valid @RequestBody ReviewRequest reviewRequest) {
        return ResponseEntity.ok(reviewMapper.toResponse(reviewService.save(reviewMapper.toEntity(reviewRequest))));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Review", description = "Delete a Review by ID")
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Integer id) {
        if (!reviewService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
