export type PostId = number;

export type Post = {
    id: PostId;
    userId: number;
    gameId?: number;
    title: string;
    description: string;
}

export type LikePostResponse = {
    userId: number;
    gameId: number;
    isLiked: boolean;
}

export type PostCreationRequest = {
    userId: number;
    gameId: number;
    title: string;
    description?: string;
    attachment?: string;
}

export type PostCreationResponse = Post;

export type PostEditionRequest = {
    userId: number;
    postId: number;
    newTitle?: string;
    newDescription?: string;
    newAttachment?: string;
}

export type PostEditionResponse = Post;
