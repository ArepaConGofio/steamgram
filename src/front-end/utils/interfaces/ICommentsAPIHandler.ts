import { Comment, CommentId } from "@/models/Comment";

export interface ICommentsAPIHandler {
  createComment(): Promise<CommentId>;

  editComment(): Promise<Comment>;

  deleteComment(): Promise<CommentId>;
}
