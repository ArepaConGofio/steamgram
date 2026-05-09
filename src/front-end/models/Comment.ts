import { PostId } from "./Post";
import { UserId } from "./User";

export type CommentId = number;

export type Comment = {
    id: CommentId;
    userId: UserId;
    postId: PostId;
    text: string;
}

export type CommentCreationRequest = Omit<Comment, "id">

export type CommentCreationResponse = Comment;

export type CommentEditionRequest = Comment;

export type CommentEditionResponse = Comment;
