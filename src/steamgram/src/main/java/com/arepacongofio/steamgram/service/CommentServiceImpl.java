package com.arepacongofio.steamgram.service;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.entities.Comment;
import com.arepacongofio.steamgram.repository.CommentJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;
import com.arepacongofio.steamgram.service.interfaces.ICommentService;

@Service
public class CommentServiceImpl extends AbstractService<Comment, Integer>implements ICommentService {

    CommentJpaRepository commentRepository;

    public CommentServiceImpl(CommentJpaRepository commentRepository) {
        super(commentRepository);
    }
}
