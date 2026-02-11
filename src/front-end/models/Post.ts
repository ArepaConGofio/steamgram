export type PostId = number;

export interface Post {
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
