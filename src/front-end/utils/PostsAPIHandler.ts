import { AuthContext } from "@/context/AuthContext";
import { LikePostResponse, LikeRequest, Post, PostCreationRequest, PostCreationResponse, PostId } from "@/models/Post";
import { useContext } from "react";
import { APIHandler, HttpMethods } from "./APIHandler";
import { IPostsAPIHandler } from "./interfaces/IPostsAPIHandler";

export class PostsAPIHandler extends APIHandler implements IPostsAPIHandler {

  async getAllPosts(limit?: number): Promise<Post[]> {
    const { token } = useContext(AuthContext);
    return await APIHandler.makeRequest({
      endpoint: "/post",
      token: token
    });
  }

  async createPost(postToCreate: PostCreationRequest): Promise<PostCreationResponse> {
    const { token } = useContext(AuthContext);
    const created = await APIHandler.makeRequest({
      endpoint: "/post",
      body: postToCreate,
      method: HttpMethods.POST,
      token: token
    })
    return created;
  }

  async deletePost(postId: PostId): Promise<boolean> {
    await APIHandler.makeRequest({
      endpoint: `/post/${postId}`,
      method: HttpMethods.DELETE
    })
    return true;
  }

  async likePost(like: LikeRequest): Promise<LikePostResponse> {
    const { token } = useContext(AuthContext);
    await APIHandler.makeRequest({
      endpoint: `/like/`,
      method: HttpMethods.POST,
      body: like,
      token: token
    })
    return { gameId: like.gameId, isLiked: true, userId: like.userId }
  }

  async dislikePost(likeId: number): Promise<boolean> {
    const { token } = useContext(AuthContext);
    await APIHandler.makeRequest({
      endpoint: `/like/${likeId}`,
      method: HttpMethods.DELETE,
      token: token
    })
    return true;
  }

}
