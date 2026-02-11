import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review } from "@/models/Review";
import { User, UserDetails } from "@/models/User";
import { APIHandler } from "./APIHandler";
import { IUsersAPIHandler } from "./interfaces/IUsersAPIHandler";

export class UsersAPIHandler extends APIHandler implements IUsersAPIHandler {
  async getAllUsers(limit?: number): Promise<User[]> {
    throw new Error("Not implemented");
  }

  async searchUserByUsername(
    username: string,
    limit?: number
  ): Promise<User[]> {
    throw new Error("Not implemented");
  }

  async searchUserByNickname(
    nickname: string,
    limit?: number
  ): Promise<User[]> {
    throw new Error("Not implemented");
  }

  async getUserDetails(userId: number): Promise<UserDetails> {
    throw new Error("Not implemented");
  }

  async getFollowers(userId: number, limit?: number): Promise<User[]> {
    throw new Error("Not implemented");
  }

  async getFollowings(userId: number, limit?: number): Promise<User[]> {
    throw new Error("Not implemented");
  }

  async getLikedGames(userId: number, limit?: number): Promise<Game[]> {
    throw new Error("Not implemented");
  }

  async getPosts(userId: number, limit?: number): Promise<Post[]> {
    throw new Error("Not implemented");
  }

  async getReviews(userId: number, limit?: number): Promise<Review[]> {
    throw new Error("Not implemented");
  }
}
