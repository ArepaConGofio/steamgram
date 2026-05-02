import { Game, GameId } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review, ReviewId } from "@/models/Review";

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
   * @param limit - Max query results.
   */
  searchGamesByTitle(title: string, limit?: number): Promise<Game[]>;

  reviewGame(review: Review): Promise<Review>;

  deleteReview(reviewId: ReviewId): Promise<boolean>

  getGameReviews(gameId: GameId): Promise<Review[]>;

  getGamePosts(gameId: GameId): Promise<Post[]>
}
