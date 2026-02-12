import { GameId } from "./Game";
import { UserId } from "./User";

export type ReviewId = number;

export type Review = {
    id: ReviewId;
    gameId: GameId;
    userId: UserId;
    content: string;
    rating: number;
}