package com.arepacongofio.steamgram.service;


import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.domain.requests.LikeCreateRequest;
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
    }

    @Override
    public Post toggleLike(LikeCreateRequest request) {
        User user = userJpaRepository.findById(request.getIdUser()).orElse(null);
            
        Post post = postRepository.findById(request.getIdPost()).orElse(null);
        
        if(user == null || post == null){
            return null;
        }
        
        Optional<Like> existLike = likeJpaRepository.findByUserAndPost(user, post);
        
        if (existLike.isPresent()) {
            Like like = existLike.get();
            likeJpaRepository.delete(like);
            post.getLikes().remove(like);
        } else {
            Like newLike = new Like(user, post);
            likeJpaRepository.save(newLike);
            post.getLikes().add(newLike);
        }
        
        return post;
    }
    
}
