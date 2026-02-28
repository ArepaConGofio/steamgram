import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review } from "@/models/Review";
import { User, UserDetails, UserId } from "@/models/User";

export interface IUsersAPIHandler {
  /**
   * Get all users. The query can be limited.
   * @param limit - Max query results.
   */
  getAllUsers(limit?: number): Promise<User[]>;

  /**
   * Search users by username. The query can be limited.
   * @param username - Username
   * @param limit - Max query results.
   */
  searchUserByUsername(username: string, limit?: number): Promise<User[]>;

  /**
   * Search user by nickname (the public name). The query can be limited.
   * @param nickname - User public name.
   * @param limit - Max query results.
   */
  searchUserByNickname(nickname: string, limit?: number): Promise<User[]>;

  /**
   * Get user details by ID.
   * @param userId - User identifier.
   */
  getUserDetailsById(userId: UserId): Promise<UserDetails>;

  /**
   * Get user details by username.
   * @param username - Username.
   */
  getUserDetailsByUsername(username: string): Promise<UserDetails>;

  /**
   * Get user followers. The query can be limited.
   * @param userId - User identifier.
   * @param limit - Max query results.
   */
  getFollowers(userId: UserId, limit?: number): Promise<User[]>;

  /**
   * Get user followings. The query can be limited.
   * @param userId - User identifier.
   * @param limit - Max query results.
   */
  getFollowings(userId: UserId, limit?: number): Promise<User[]>;

  /**
   * Get user game list. The query can be limited.
   * @param userId - User identifier.
   * @param limit - Max query results.
   */
  getLikedGames(userId: UserId, limit?: number): Promise<Game[]>;

  /**
   * Get user posts. The query can be limited.
   * @param userId - User identifier.
   * @param limit - Max query results.
   */
  getPosts(userId: UserId, limit?: number): Promise<Post[]>;

  /**
   * Get user reviews. The query can be limited.
   * @param userId - User identifier.
   * @param limit - Max query results.
   */
  getReviews(userId: UserId, limit?: number): Promise<Review[]>;
}
