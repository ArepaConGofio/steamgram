import { Comment } from "@/models/Comment";
import {
  LikePostResponse,
  Post,
  PostCreationRequest,
  PostEditionRequest,
  PostId,
} from "@/models/Post";
import { UserId } from "@/models/User";

export interface IPostsAPIHandler {
  /**
   * Get all posts. The query can be limited.
   * @param limit - Max query results.
   */
  getAllPosts(limit?: number): Promise<Post[]>;

  /**
   * Get all post comments. The query can be limited.
   * @param postId - Post identifier.
   * @param limit - Max query results.
   */
  getComments(postId: PostId, limit?: number): Promise<Comment[]>;

  /**
   * Create a new post.
   * @param postToCreate - Request to create a post, including user,
   * attached game, title, optional description and optional attachment.
   */
  createPost(postToCreate: PostCreationRequest): Promise<Post>;

  /**
   * Edit an existent post.
   * @param postToEdit - Request to edit a post. Only changes values was implemented.
   * The available values to change are title, description and attachment.
   */
  editPost(postToEdit: PostEditionRequest): Promise<Post>;

  /**
   * Delete an existent post.
   * @param userId - User requesting.
   * @param postId - Post identifier.
   */
  deletePost(userId: UserId, postId: PostId): Promise<boolean>;

  /**
   * Like/dislike a post as user.
   * @param userId - User requesting.
   * @param postId - Post to like/dislike.
   */
  likePost(userId: UserId, postId: PostId): Promise<LikePostResponse>;
}
