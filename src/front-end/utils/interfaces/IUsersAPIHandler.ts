import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review } from "@/models/Review";
import { User, UserDetails } from "@/models/User";

export interface IUsersAPIHandler {
  /**
   * Get all users. The query can be limited.
   * @param limit - Max users.
   */
  getAllUsers(limit?: number): Promise<User[]>;

  searchUserByUsername(username: string, limit?: number): Promise<User[]>;

  searchUserByNickname(nickname: string, limit?: number): Promise<User[]>;

  getUserDetails(userId: number): Promise<UserDetails>;

  getFollowers(userId: number, limit?: number): Promise<User[]>;

  getFollowings(userId: number, limit?: number): Promise<User[]>;

  getLikedGames(userId: number, limit?: number): Promise<Game[]>;

  getPosts(userId: number, limit?: number): Promise<Post[]>;

  getReviews(userId: number, limit?: number): Promise<Review[]>;
}
