export type PostId = number;

export type Post = {
    id: PostId;
    userId: number;
    author: string;
    gameId: number;
    gameTitle: string;
    title: string;
    description: string;
    likesCount: number;
    creationDate: string;
}

export type LikePostResponse = {
    userId: number;
    gameId: number;
    isLiked: boolean;
}

export type LikeRequest = {
    userId: number;
    gameId: number;
}

export type PostCreationRequest = {
    userId: number;
    gameId: number;
    title: string;
    description?: string;
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
