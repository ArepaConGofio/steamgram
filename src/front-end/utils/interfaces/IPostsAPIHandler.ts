import { Comment } from "@/models/Comment";
import { LikePostResponse, Post, PostId } from "@/models/Post";

export interface IPostsAPIHandler {
  getAllPosts(): Promise<Post[]>;

  getComments(): Promise<Comment[]>;

  createPost(): Promise<PostId>;

  editPost(): Promise<Post>;

  deletePost(): Promise<PostId>;

  likePost(): Promise<LikePostResponse>;
}
