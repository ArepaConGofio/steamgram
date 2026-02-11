import { Comment } from "@/models/Comment";
import { LikePostResponse, Post, PostId } from "@/models/Post";
import { APIHandler } from "./APIHandler";
import { IPostsAPIHandler } from "./interfaces/IPostsAPIHandler";

export class PostsAPIHandler extends APIHandler implements IPostsAPIHandler {
  async getAllPosts(): Promise<Post[]> {
    throw new Error("Not implemented");
  }

  async getComments(): Promise<Comment[]> {
    throw new Error("Not implemented");
  }

  async createPost(): Promise<PostId> {
    throw new Error("Not implemented");
  }

  async editPost(): Promise<Post> {
    throw new Error("Not implemented");
  }

  async deletePost(): Promise<PostId> {
    throw new Error("Not implemented");
  }

  async likePost(): Promise<LikePostResponse> {
    throw new Error("Not implemented");
  }
}
