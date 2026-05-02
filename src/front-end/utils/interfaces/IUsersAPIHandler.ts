import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review } from "@/models/Review";
import { User, UserDetails, UserId } from "@/models/User";

export interface IUsersAPIHandler {
  /**
   * Search users by username. The query can be limited.
   * @param username - Username
   */
  searchUserByUsername(username: string): Promise<User[]>;

  /**
   * Search user by nickname (the public name). The query can be limited.
   * @param nickname - User public name.
   */
  searchUserByNickname(nickname: string): Promise<User[]>;

  /**
   * Get user details by username.
   * @param username - Username.
   */
  getUserDetailsByUsername(username: string): Promise<UserDetails>;

  /**
   * Get user followers. The query can be limited.
   * @param userId - User identifier.
   */
  getFollowers(userId: UserId): Promise<User[]>;

  /**
   * Get user followings. The query can be limited.
   * @param userId - User identifier.
   */
  getFollowings(userId: UserId): Promise<User[]>;

  /**
   * Get user game list. The query can be limited.
   * @param userId - User identifier.
   */
  getLikedGames(userId: UserId): Promise<Game[]>;

  /**
   * Get user posts. The query can be limited.
   * @param userId - User identifier.
   */
  getPosts(userId: UserId): Promise<Post[]>;

  /**
   * Get user reviews. The query can be limited.
   * @param userId - User identifier.
   */
  getReviews(userId: UserId): Promise<Review[]>;
}
