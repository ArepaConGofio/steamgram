import { LikeRequest, Post, PostCreationRequest, PostCreationResponse, PostId } from "@/models/Post";
import { APIHandler, HttpMethods } from "./APIHandler";
import { IPostsAPIHandler } from "./interfaces/IPostsAPIHandler";

export class PostsAPIHandler extends APIHandler implements IPostsAPIHandler {

  async getAllPosts(limit?: number): Promise<Post[]> {
    const response = await APIHandler.makeRequest({
      endpoint: "/post",
      token: true
    });
    return response;
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
      method: HttpMethods.DELETE,
      token: true
    })
    return true;
  }

  async likePost(like: LikeRequest): Promise<boolean> {
    const response = await APIHandler.makeRequest({
      endpoint: `/post/like`,
      method: HttpMethods.POST,
      body: like,
      token: true
    })
    return response.liked;
  }

}
