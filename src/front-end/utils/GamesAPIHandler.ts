import { AuthContext } from "@/context/AuthContext";
import { Game, GameId } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review, ReviewCreationRequest, ReviewId } from "@/models/Review";
import { useContext } from "react";
import { APIHandler, HttpMethods } from "./APIHandler";
import { IGamesAPIHandler } from "./interfaces/IGamesAPIHandler";

export class GamesAPIHandler extends APIHandler implements IGamesAPIHandler {

  async getAllGames(): Promise<Game[]> {
    const { token } = useContext(AuthContext);
    return await APIHandler.makeRequest({ 
      endpoint: `/game`,
      token: token
    });
  }
  
  async getGameDetails(gameId: GameId): Promise<Game | undefined> {
    const game = await APIHandler.makeRequest({ endpoint: `/game/${gameId}` })
    if (!game) {
      throw new Error("ERROR: Game not found")
    }
    return game;
  }

  async searchGamesByTitle(title: string): Promise<Game[]> {
    const { token } = useContext(AuthContext);
    return await APIHandler.makeRequest({ 
      endpoint: `/game/findByTitle/${title}?pageSize=10`,
      token: token,
    })
  }

  async reviewGame(review: ReviewCreationRequest): Promise<Review> {
    const { token } = useContext(AuthContext)
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
      token: token
    });
    return response;
  }

  async deleteReview(reviewId: ReviewId): Promise<boolean> {
    const { token } = useContext(AuthContext);
    await APIHandler.makeRequest({
      endpoint: `/review/${reviewId}`,
      method: HttpMethods.DELETE,
      token: token
    });
    return true;
  }
  
  async getGameReviews(gameId: GameId): Promise<Review[]> {
    const { token } = useContext(AuthContext);
    return APIHandler.makeRequest({
      endpoint: `/game/reviews/${gameId}`,
      token: token
    })
  }

  async getGamePosts(gameId: GameId): Promise<Post[]> {
    const { token } = useContext(AuthContext);
    return APIHandler.makeRequest({
      endpoint: `/game/posts/${gameId}`,
      token: token
    })  
  } 
}
