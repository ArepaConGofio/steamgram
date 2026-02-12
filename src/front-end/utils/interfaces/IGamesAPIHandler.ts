import { Game, GameCreationRequest, GameId } from "@/models/Game";
import { Review } from "@/models/Review";

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

  /**
   * Create a new game as developer.
   * @param gameToCreate - Game creation request including name, cover url,
   * description and optional Steam identifier and publisher name.
   */
  createGame(gameToCreate: GameCreationRequest): Promise<Game>;

  reviewGame(review: Review): Promise<Review>;
}
