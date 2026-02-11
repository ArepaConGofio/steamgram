import { Comment, CommentId } from "@/models/Comment";
import { APIHandler } from "./APIHandler";

export class CommentsAPIHandler extends APIHandler {
  async createComment(): Promise<CommentId> {
    throw new Error("Not implemented");
  }

  async editComment(): Promise<Comment> {
    throw new Error("Not implemented");
  }

  async deleteComment(): Promise<CommentId> {
    throw new Error("Not implemented");
  }
}
