import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review } from "@/models/Review";
import { User, UserDetails, UserId } from "@/models/User";
import { APIHandler } from "./APIHandler";
import { IUsersAPIHandler } from "./interfaces/IUsersAPIHandler";

export class UsersAPIHandler extends APIHandler implements IUsersAPIHandler {
  getAllUsers(limit?: number): Promise<User[]> {
    throw new Error("Method not implemented.");
  }
  searchUserByUsername(username: string, limit?: number): Promise<User[]> {
    throw new Error("Method not implemented.");
  }
  searchUserByNickname(nickname: string, limit?: number): Promise<User[]> {
    throw new Error("Method not implemented.");
  }
  getUserDetails(userId: UserId): Promise<UserDetails> {
    throw new Error("Method not implemented.");
  }
  getFollowers(userId: UserId, limit?: number): Promise<User[]> {
    throw new Error("Method not implemented.");
  }
  getFollowings(userId: UserId, limit?: number): Promise<User[]> {
    throw new Error("Method not implemented.");
  }
  getLikedGames(userId: UserId, limit?: number): Promise<Game[]> {
    throw new Error("Method not implemented.");
  }
  getPosts(userId: UserId, limit?: number): Promise<Post[]> {
    throw new Error("Method not implemented.");
  }
  getReviews(userId: UserId, limit?: number): Promise<Review[]> {
    throw new Error("Method not implemented.");
  }
}
