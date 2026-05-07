import { LikePostResponse, LikeRequest, Post, PostCreationRequest, PostCreationResponse, PostId } from "@/models/Post";
import { APIHandler, HttpMethods } from "./APIHandler";
import { IPostsAPIHandler } from "./interfaces/IPostsAPIHandler";

export class PostsAPIHandler extends APIHandler implements IPostsAPIHandler {

  async getAllPosts(limit?: number): Promise<Post[]> {
    return await APIHandler.makeRequest({
      endpoint: "/post",
      token: true
    });
  }

  async createPost(postToCreate: PostCreationRequest): Promise<PostCreationResponse> {
    const created = await APIHandler.makeRequest({
      endpoint: "/post",
      body: postToCreate,
      method: HttpMethods.POST,
      token: true
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
    await APIHandler.makeRequest({
      endpoint: `/like/`,
      method: HttpMethods.POST,
      body: like,
      token: true
    })
    return { gameId: like.gameId, isLiked: true, userId: like.userId }
  }

  async dislikePost(likeId: number): Promise<boolean> {
    await APIHandler.makeRequest({
      endpoint: `/like/${likeId}`,
      method: HttpMethods.DELETE,
      token: true
    })
    return true;
  }

}
