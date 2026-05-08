import { Game, GameId } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review, ReviewCreationRequest, ReviewId } from "@/models/Review";
import { APIHandler, HttpMethods } from "./APIHandler";
import { IGamesAPIHandler } from "./interfaces/IGamesAPIHandler";

export class GamesAPIHandler extends APIHandler implements IGamesAPIHandler {

  async getAllGames(): Promise<Game[]> {
    return await APIHandler.makeRequest({ 
      endpoint: `/game`,
      token: true
    });
  }
  
  async getGameDetails(gameId: GameId): Promise<Game | undefined> {
    const game = await APIHandler.makeRequest({ endpoint: `/game/${gameId}`, token: true })
    if (!game) {
      throw new Error("ERROR: Game not found")
    }
    return game;
  }

  async searchGamesByTitle(title: string): Promise<Game[]> {
    return await APIHandler.makeRequest({ 
      endpoint: `/game/findByTitle/${title}?pageSize=10`,
      token: true,
    })
  }

  async reviewGame(review: ReviewCreationRequest): Promise<Review> {
    const response = await APIHandler.makeRequest({
      endpoint: "/review",
      method: HttpMethods.POST,
      body: {
        idUser: review.userId,
        idGame: review.gameId,
        title: review.title,
        description: review.description,
        rating: review.rating
      },
      token: true
    });
    return response;
  }

  async deleteReview(reviewId: ReviewId): Promise<boolean> {
    await APIHandler.makeRequest({
      endpoint: `/review/${reviewId}`,
      method: HttpMethods.DELETE,
      token: true
    });
    return true;
  }
  
  async getGameReviews(gameId: GameId): Promise<Review[]> {
    return APIHandler.makeRequest({
      endpoint: `/game/reviews/${gameId}`,
      token: true
    })
  }

  async getGamePosts(gameId: GameId): Promise<Post[]> {
    return APIHandler.makeRequest({
      endpoint: `/game/posts/${gameId}`,
      token: true
    })  
  } 
}
