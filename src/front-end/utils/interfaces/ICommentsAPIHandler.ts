import { CommentCreationRequest, CommentCreationResponse, CommentEditionRequest, CommentEditionResponse, CommentId } from "@/models/Comment";
import { UserId } from "@/models/User";

export interface ICommentsAPIHandler {
  /**
   * Create a comment for an existent post.
   * @param commentToCreate - Comment creation request including post identifier,
   * user identifier and content.
   */
  createComment(commentToCreate: CommentCreationRequest): Promise<CommentCreationResponse>;

  /**
   * Edit an existent comment.
   * @param commentToEdit - Comment edition request.
   */
  editComment(commentToEdit: CommentEditionRequest): Promise<CommentEditionResponse>;

  /**
   * Delete an existent comment by identifier.
   * @param userId - User requesting identifier.
   * @param commentId - Comment identifier.
   */
  deleteComment(userId: UserId, commentId: CommentId): Promise<boolean>;
}
