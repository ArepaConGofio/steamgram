import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review } from "@/models/Review";
import { User, UserDetails, UserId } from "@/models/User";
import { APIHandler } from "./APIHandler";
import { IUsersAPIHandler } from "./interfaces/IUsersAPIHandler";

export class UsersAPIHandler extends APIHandler implements IUsersAPIHandler {
  getUserDetailsByUsername(username: string): Promise<UserDetails> {
    throw new Error("Method not implemented.");
  }
  getUserDetails(userId: UserId): Promise<UserDetails> {
    throw new Error("Method not implemented.");
  }
  getAllUsers(limit?: number): Promise<User[]> {
    throw new Error("Method not implemented.");
  }
  searchUserByUsername(username: string, limit?: number): Promise<User[]> {
    throw new Error("Method not implemented.");
  }
  searchUserByNickname(nickname: string, limit?: number): Promise<User[]> {
    throw new Error("Method not implemented.");
  }
  getUserDetailsById(userId: UserId): Promise<UserDetails> {
    throw new Error("Method not implemented.");
  }
  
  static async getUserDetailsByUsername(username: string): Promise<UserDetails> {
    // TODO: Make real request to API
    const mockUserData: UserDetails = {
      id: 1,
      username: username,
      email: "test@mail.com",
      followersCount: 10,
      followingCount: 20,
      gamesCount: 3,
      postsCount: 5,
      reviewsCount: 7
    };
    return new Promise(resolve => {
      setTimeout(() => resolve(mockUserData), 1000)
    });
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
