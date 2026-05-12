package com.arepacongofio.steamgram.domain.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.arepacongofio.steamgram.domain.requests.CommentRequest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CommentRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void gettersAndSettersTest() {
        CommentRequest request = new CommentRequest();
        Integer expectedIdUser = 1;
        Integer expectedIdPost = 42;
        String expectedText = "¡Qué juegazo!";
        request.setIdUser(expectedIdUser);
        request.setIdPost(expectedIdPost);
        request.setText(expectedText);

        assertEquals(expectedIdUser, request.getIdUser());
        assertEquals(expectedIdPost, request.getIdPost());
        assertEquals(expectedText, request.getText());
    }

    @Test
    void validCommentRequestTest() {
        CommentRequest request = new CommentRequest();
        request.setIdUser(1);
        request.setIdPost(42);
        request.setText("Comentario válido");

        Set<ConstraintViolation<CommentRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    @Test
    void nullIdUserTest() {
        CommentRequest request = new CommentRequest();
        request.setIdUser(null); 
        request.setIdPost(42);
        request.setText("Comentario sin usuario");

        Set<ConstraintViolation<CommentRequest>> violations = validator.validate(request);

        assertEquals(1, violations.size());
        ConstraintViolation<CommentRequest> violation = violations.iterator().next();
        assertEquals("idUser", violation.getPropertyPath().toString());
    }

    @Test
    void nullIdPostTest() {
        CommentRequest request = new CommentRequest();
        request.setIdUser(1);
        request.setIdPost(null); 
        request.setText("Comentario sin post");

        Set<ConstraintViolation<CommentRequest>> violations = validator.validate(request);

        assertEquals(1, violations.size());
        ConstraintViolation<CommentRequest> violation = violations.iterator().next();
        assertEquals("idPost", violation.getPropertyPath().toString());
    }

    @Test
    void nullIdUserAndIdPostTest() {
        CommentRequest request = new CommentRequest();
        request.setIdUser(null);
        request.setIdPost(null);
        request.setText("Todo nulo menos el texto");

        Set<ConstraintViolation<CommentRequest>> violations = validator.validate(request);

        assertEquals(2, violations.size());
    }
    
    @Test
    void textCanBeNullTest() {
        CommentRequest request = new CommentRequest();
        request.setIdUser(1);
        request.setIdPost(42);
        request.setText(null); 
        
        Set<ConstraintViolation<CommentRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }
}