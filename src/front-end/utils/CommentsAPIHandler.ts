import { Comment, CommentCreationRequest, CommentEditionRequest, CommentId } from "@/models/Comment";
import { UserId } from "@/models/User";
import { APIHandler } from "./APIHandler";
import { ICommentsAPIHandler } from "./interfaces/ICommentsAPIHandler";

export class CommentsAPIHandler extends APIHandler implements ICommentsAPIHandler {
  createComment(commentToCreate: CommentCreationRequest): Promise<CommentId> {
    throw new Error("Method not implemented.");
  }
  editComment(commentToEdit: CommentEditionRequest): Promise<Comment> {
    throw new Error("Method not implemented.");
  }
  deleteComment(userId: UserId, commentId: CommentId): Promise<boolean> {
    throw new Error("Method not implemented.");
  }
  
}
