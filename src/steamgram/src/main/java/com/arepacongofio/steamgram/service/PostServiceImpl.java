package com.arepacongofio.steamgram.service;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.domain.requests.LikeCreateRequest;
import com.arepacongofio.steamgram.domain.responses.LikePostResponse;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.entities.Like;
import com.arepacongofio.steamgram.repository.PostJpaRepository;
import com.arepacongofio.steamgram.repository.UserJpaRepository;
import com.arepacongofio.steamgram.repository.LikeJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;
import com.arepacongofio.steamgram.service.interfaces.IPostService;

import java.util.Optional;

@Service
public class PostServiceImpl extends AbstractService<Post,Integer> implements IPostService{

    PostJpaRepository postRepository;
    UserJpaRepository userJpaRepository;
    LikeJpaRepository likeJpaRepository;
    
    public PostServiceImpl(PostJpaRepository postRepository, UserJpaRepository userJpaRepository, LikeJpaRepository likeJpaRepository) {
        super(postRepository);
        this.postRepository = postRepository;
        this.userJpaRepository = userJpaRepository;
        this.likeJpaRepository = likeJpaRepository;
        this.postRepository = postRepository;
    }    

    public LikePostResponse toggleLike(LikeCreateRequest request) {
        User user = userJpaRepository.findById(request.getIdUser()).orElse(null);
            
        Post post = postRepository.findById(request.getIdPost()).orElse(null);
        
        if(user == null || post == null){
            return null;
        }
        
        Optional<Like> likeOptional = likeJpaRepository.findByUserAndPost(user, post);
        boolean likeExists = likeOptional.isPresent();
        
        if (likeExists) {
            Like like = likeOptional.get();
            likeJpaRepository.delete(like);
            post.getLikes().remove(like);
        } else {
            Like newLike = new Like(user, post);
            likeJpaRepository.save(newLike);
            post.getLikes().add(newLike);
        }
        return new LikePostResponse(post.getId(), user.getId(), likeExists);
    }

    @Override
    public Post save(Post entity) {
        if (entity.getId() == null) {
            entity.setPublicationDate(LocalDateTime.now());
        }
        return super.save(entity);
    }
    
}
