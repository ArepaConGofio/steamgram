import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review } from "@/models/Review";
import { User, UserDetails } from "@/models/User";
import { APIHandler } from "./APIHandler";

export class UsersAPIHandler extends APIHandler {
  async getAllUsers(): Promise<User[]> {
    throw new Error("Not implemented");
  }

  async searchUserByUsername(): Promise<User[]> {
    throw new Error("Not implemented");
  }

  async searchUserByNickname(): Promise<User[]> {
    throw new Error("Not implemented");
  }

  async getUserDetails(): Promise<UserDetails | undefined> {
    throw new Error("Not implemented");
  }

  async getFollowers(): Promise<User[]> {
    throw new Error("Not implemented");
  }

  async getFollowings(): Promise<User[]> {
    throw new Error("Not implemented");
  }

  async getLikedGames(): Promise<Game[]> {
    throw new Error("Not implemented");
  }

  async getPosts(): Promise<Post[]> {
    throw new Error("Not implemented");
  }

  async getReviews(): Promise<Review[]> {
    throw new Error("Not implemented");
  }
}
