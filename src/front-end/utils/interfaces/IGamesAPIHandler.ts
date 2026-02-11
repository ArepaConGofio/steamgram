import { Game } from "@/models/Game";

export interface IGamesAPIHandler {
  getAllGames(): Promise<Game[]>;

  getGameDetails(): Promise<Game | undefined>;

  searchGamesByTitle(): Promise<Game[]>;

  createGame(): Promise<Game>;
}
