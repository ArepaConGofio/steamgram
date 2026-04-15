import { Game, GameCreationRequest, GameCreationResponse, GameId } from "@/models/Game";
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

  searchGamesByTitle(title: string, limit?: number): Promise<Game[]> {
    throw new Error("Method not implemented.");
  }
  createGame(gameToCreate: GameCreationRequest): Promise<GameCreationResponse> {
    throw new Error("Method not implemented.");
  }
  reviewGame(review: Review): Promise<Review> {
    throw new Error("Method not implemented.");
  }
}
