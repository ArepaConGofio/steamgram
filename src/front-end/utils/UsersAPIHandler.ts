import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review } from "@/models/Review";
import { User, UserDetails, UserId } from "@/models/User";
import { APIHandler } from "./APIHandler";
import { IUsersAPIHandler } from "./interfaces/IUsersAPIHandler";

export class UsersAPIHandler extends APIHandler implements IUsersAPIHandler {
  getAllUsers(): Promise<User[]> {
    throw new Error("Method not implemented.");
  }
  async getUserDetailsByUsername(username: string): Promise<UserDetails> {
    const users = await APIHandler.makeRequest({ endpoint: `/users?username=${username}` });
    if (!users || users[0].username != username) {
      throw new Error("ERROR: User not found");
    }
    return users[0];
  }
  searchUserByUsername(username: string): Promise<User[]> {
    throw new Error("Method not implemented.")
  }
  searchUserByNickname(nickname: string): Promise<User[]> {
    throw new Error("Method not implemented.");
  }
  getFollowers(userId: UserId): Promise<User[]> {
    return new Promise(resolve => {
      resolve([])
    })
  }
  getFollowings(userId: UserId): Promise<User[]> {
    return new Promise(resolve => {
      resolve([])
    })
  }
  getLikedGames(userId: UserId): Promise<Game[]> {
    return new Promise(resolve => {
      resolve([])
    })
  }
  getPosts(userId: UserId): Promise<Post[]> {
    return new Promise(resolve => {
      resolve([])
    })
  }
  getReviews(userId: UserId): Promise<Review[]> {
    return new Promise(resolve => {
      resolve([])
    })
  }
}
