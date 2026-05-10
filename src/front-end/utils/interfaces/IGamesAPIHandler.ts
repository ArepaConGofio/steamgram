import { Game, GameId, GameSearchResponse } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review, ReviewId } from "@/models/Review";
import { UserId } from "@/models/User";

export interface IGamesAPIHandler {
  /**
   * Get all games. The query can be limited.
   * @param limit - Max query results.
   */
  getAllGames(limit?: number): Promise<Game[]>;

  /**
   * Get game details.
   * @param gameId - Game identifier.
   */
  getGameDetails(gameId: GameId): Promise<Game | undefined>;

  /**
   * Search games by title. The query can be limited.
   * @param title - Game title.
   */
  searchGamesByTitle(title: string): Promise<GameSearchResponse[]>;

  reviewGame(review: Review): Promise<Review>;

  deleteReview(reviewId: ReviewId): Promise<boolean>

  getGameReviews(gameId: GameId): Promise<Review[]>;

  getGamePosts(gameId: GameId): Promise<Post[]>;

  saveGameIntoLibrary(gameId: GameId, userId: UserId): Promise<boolean>;
}
