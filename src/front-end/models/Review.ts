import { GameId } from "./Game";
import { UserId } from "./User";

export type ReviewId = number;

export type Review = {
    id: ReviewId;
    gameId: GameId;
    gameTitle: string;
    userId: UserId;
    author: string;
    title: string;
    description?: string;
    rating: number;
}

export type ReviewCreationRequest = Omit<Review, "id">

export type ReviewCreationResponse = Review;
