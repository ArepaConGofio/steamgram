import { Game } from "@/models/Game";
import { APIHandler } from "./APIHandler";

export class GamesAPIHandler extends APIHandler {
    async getAllGames(): Promise<Game[]> {
        throw new Error("Not implemented")
    }

    async getGameDetails(): Promise<Game|undefined> {
        throw new Error("Not implemented")
    }

    async searchGamesByTitle(): Promise<Game[]> {
        throw new Error("Not implemented")
    }

    /**
     * For game devs
     */
    async createGame(): Promise<Game> {
        throw new Error("Not implemented");
    }
}