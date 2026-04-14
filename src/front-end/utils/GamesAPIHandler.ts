import { Game, GameCreationRequest, GameCreationResponse, GameId } from "@/models/Game";
import { Review } from "@/models/Review";
import { APIHandler } from "./APIHandler";
import { IGamesAPIHandler } from "./interfaces/IGamesAPIHandler";

export class GamesAPIHandler extends APIHandler implements IGamesAPIHandler {
  async getAllGames(limit?: number): Promise<Game[]> {
    return await APIHandler.makeRequest({ endpoint: `/games` });
  }
  
  getGameDetails(gameId: GameId): Promise<Game | undefined> {
    throw new Error("Method not implemented.");
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
