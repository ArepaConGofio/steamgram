export type CommentId = number;

export interface Comment {
    id: CommentId;
    userId: number;
    postId: number;
    text: string;
}