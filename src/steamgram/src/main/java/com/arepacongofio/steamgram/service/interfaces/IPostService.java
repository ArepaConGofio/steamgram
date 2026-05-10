package com.arepacongofio.steamgram.service.interfaces;

import com.arepacongofio.steamgram.domain.requests.LikeCreateRequest;
import com.arepacongofio.steamgram.domain.responses.LikePostResponse;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.service.interfaces.generic.IGenericService;

/**
 * Post service. Manage basic operation with post entities.
 * 
 * @author strSalazar.
 */
public interface IPostService extends IGenericService<Post, Integer> {

    /**
     * If the post doesn't have a like from the user, it adds one, if it has one, it
     * removes it
     * 
     * @param request with the id of the user and the post
     * @return Post
     */
    public LikePostResponse toggleLike(LikeCreateRequest request);

}
