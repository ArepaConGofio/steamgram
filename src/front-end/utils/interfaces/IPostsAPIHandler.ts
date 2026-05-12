import {
  LikePostResponse,
  LikeRequest,
  Post,
  PostCreationRequest,
  PostId
} from "@/models/Post";
import { UserId } from "@/models/User";

export interface IPostsAPIHandler {
  /**
   * Get all posts. The query can be limited.
   * @param limit - Max query results.
   */
  getAllPosts(limit?: number): Promise<Post[]>;

  /**
   * Create a new post.
   * @param postToCreate - Request to create a post, including user,
   * attached game, title, optional description and optional attachment.
   */
  createPost(postToCreate: PostCreationRequest): Promise<Post>;

  /**
   * Delete an existent post.
   * @param userId - User requesting.
   * @param postId - Post identifier.
   */
  deletePost(userId: UserId, postId: PostId): Promise<boolean>;

  /**
   * Like a post as user.
   * @param like - Like request with user and post identification
   */
  likePost(like: LikeRequest): Promise<LikePostResponse>;
}
