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
    followersCount: number;
    followingCount: number;
    reviewsCount: number;
    postsCount: number;
}

export type ContentType = "Games"|"Posts"|"Reviews";

export type ProfileContentType = ContentType|"Following"|"Followers";
