export type CommentId = number;

export type Comment = {
    id: CommentId;
    userId: number;
    postId: number;
    text: string;
}