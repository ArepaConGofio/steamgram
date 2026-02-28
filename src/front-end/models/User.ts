export type UserId = number;

export type User = {
    id: UserId;
    username: string;
    email: string;
    avatarUrl?: string;
    steamId?: string;
    nickname?: string;
}

export type UserDetails = User & {
    bio?: string;
    gamesCount: number;
    followersCount: number;
    followingCount: number;
    reviewsCount: number;
    postsCount: number;
}
