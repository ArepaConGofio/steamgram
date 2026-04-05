import { CommentCreationRequest, CommentCreationResponse, CommentEditionRequest, CommentEditionResponse, CommentId } from "@/models/Comment";
import { UserId } from "@/models/User";
import { APIHandler } from "./APIHandler";
import { ICommentsAPIHandler } from "./interfaces/ICommentsAPIHandler";

export class CommentsAPIHandler extends APIHandler implements ICommentsAPIHandler {
  createComment(commentToCreate: CommentCreationRequest): Promise<CommentCreationResponse> {
    throw new Error("Method not implemented.");
  }
  editComment(commentToEdit: CommentEditionRequest): Promise<CommentEditionResponse> {
    throw new Error("Method not implemented.");
  }
  deleteComment(userId: UserId, commentId: CommentId): Promise<boolean> {
    throw new Error("Method not implemented.");
  }
  
}
