import { Comment } from "@/models/Comment";
import { LikePostResponse, Post, PostCreationRequest, PostCreationResponse, PostId } from "@/models/Post";
import { UserId } from "@/models/User";
import { APIHandler, HttpMethods } from "./APIHandler";
import { IPostsAPIHandler } from "./interfaces/IPostsAPIHandler";

export class PostsAPIHandler extends APIHandler implements IPostsAPIHandler {

  async getAllPosts(limit?: number): Promise<Post[]> {
    return await APIHandler.makeRequest({ endpoint: "/posts" }); 
  }

  getComments(postId: PostId, limit?: number): Promise<Comment[]> {
    throw new Error("Method not implemented.");
  }

  async createPost(postToCreate: PostCreationRequest): Promise<PostCreationResponse> {
    await APIHandler.makeRequest({ endpoint: "/posts", body: postToCreate, method: HttpMethods.POST }) 
    throw new Error("Method not implemented.");
  }
  
  async deletePost(userId: UserId, postId: PostId): Promise<boolean> {
    throw new Error("Method not implemented.");
  }

  likePost(userId: UserId, postId: PostId): Promise<LikePostResponse> {
    throw new Error("Method not implemented.");
  }

}
