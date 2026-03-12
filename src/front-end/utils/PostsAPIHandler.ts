import { Comment } from "@/models/Comment";
import { LikePostResponse, Post, PostCreationRequest, PostCreationResponse, PostEditionRequest, PostEditionResponse, PostId } from "@/models/Post";
import { UserId } from "@/models/User";
import { APIHandler } from "./APIHandler";
import { IPostsAPIHandler } from "./interfaces/IPostsAPIHandler";

export class PostsAPIHandler extends APIHandler implements IPostsAPIHandler {
  getAllPosts(limit?: number): Promise<Post[]> {
    throw new Error("Method not implemented.");
  }
  getComments(postId: PostId, limit?: number): Promise<Comment[]> {
    throw new Error("Method not implemented.");
  }
  createPost(postToCreate: PostCreationRequest): Promise<PostCreationResponse> {
    throw new Error("Method not implemented.");
  }
  editPost(postToEdit: PostEditionRequest): Promise<PostEditionResponse> {
    throw new Error("Method not implemented.");
  }
  deletePost(userId: UserId, postId: PostId): Promise<boolean> {
    throw new Error("Method not implemented.");
  }
  likePost(userId: UserId, postId: PostId): Promise<LikePostResponse> {
    throw new Error("Method not implemented.");
  }

}
