import { GameId } from "./Game";
import { UserId } from "./User";

export type ReviewId = number;

export type Review = {
    id: ReviewId;
    idGame: GameId;
    gameName: string;
    idUser: UserId;
    nicknameUser: string;
    title: string;
    description?: string;
    rating: number;
}

export type ReviewCreationRequest = Omit<Review, "id">

export type ReviewCreationResponse = Review;
