export type UserId = number;

export type User = {
    id: UserId;
    username: string;
    email: string;
    avatarUrl?: string;
    nickname?: string;
}

export type UserDetails = User & {
    gamesCount: number;
    reviewsCount: number;
    postsCount: number;
}

export type ContentType = "Games"|"Posts"|"Reviews";

export type UserEditRequest = {
    id: UserId;
    name?: string;
    avatarUrl?: string;
}
