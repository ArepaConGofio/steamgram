import { Game, GameId } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review } from "@/models/Review";
import { APIHandler } from "./APIHandler";
import { IGamesAPIHandler } from "./interfaces/IGamesAPIHandler";

export class GamesAPIHandler extends APIHandler implements IGamesAPIHandler {

  async getAllGames(limit?: number): Promise<Game[]> {
    return await APIHandler.makeRequest({ endpoint: `/games` });
  }
  
  async getGameDetails(gameId: GameId): Promise<Game | undefined> {
    const games = await APIHandler.makeRequest({ endpoint: `/games?id=${gameId}` })
    const game = games[0];
    if (game == undefined || game == null) {
      throw new Error("ERROR: Game not found")
    }
    return game;
  }

  async searchGamesByTitle(title: string, limit?: number): Promise<Game[]> {
    return await APIHandler.makeRequest({ endpoint: `/games?name:contains=${title}` })
  }

  reviewGame(review: Review): Promise<Review> {
    throw new Error("Method not implemented.");
  }
  
  async getGameReviews(gameId: GameId): Promise<Review[]> {
    return await APIHandler.makeRequest({ endpoint: `/reviews?gameId=${gameId}` })
  }

  async getGamePosts(gameId: GameId): Promise<Post[]> {
    return await APIHandler.makeRequest({ endpoint: `/posts?gameId=${gameId}` })
  }
  
}
