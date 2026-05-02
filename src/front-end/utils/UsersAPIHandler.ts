import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review } from "@/models/Review";
import { User, UserDetails, UserId } from "@/models/User";
import { APIHandler } from "./APIHandler";
import { IUsersAPIHandler } from "./interfaces/IUsersAPIHandler";

export class UsersAPIHandler extends APIHandler implements IUsersAPIHandler {
  
  async getUserDetailsByUsername(username: string): Promise<UserDetails> {
    const users = await APIHandler.makeRequest({ endpoint: `/users?username=${username}` });
    if (!users || users[0].username !== username) {
      throw new Error("ERROR: User not found");
    }
    return users[0];
  }

  async searchUserByUsername(username: string): Promise<User[]> {
    return await APIHandler.makeRequest({ endpoint: `/users?username:contains=${username}` });
  }

  async searchUserByNickname(nickname: string): Promise<User[]> {
    return await APIHandler.makeRequest({ endpoint: `/users?nickname:contains=${nickname}` });
  }

  async getFollowers(userId: UserId): Promise<User[]> {
    return await APIHandler.makeRequest({ endpoint: `/users` });
  }

  async getFollowings(userId: UserId): Promise<User[]> {
    return await APIHandler.makeRequest({ endpoint: `/users?id=1` });
  }
  
  async getLikedGames(userId: UserId): Promise<Game[]> {
    return await APIHandler.makeRequest({ endpoint: `/games` });
  }

  async getPosts(userId: UserId): Promise<Post[]> {
    return await APIHandler.makeRequest({ endpoint: `/posts?userId=${userId}` })
  }
  
  async getReviews(userId: UserId): Promise<Review[]> {
    return await APIHandler.makeRequest({ endpoint: `/reviews?userId=${userId}` })
  }
}
