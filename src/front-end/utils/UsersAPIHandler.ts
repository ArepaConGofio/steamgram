import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review } from "@/models/Review";
import { User, UserDetails, UserEditRequest, UserId } from "@/models/User";
import { APIHandler, HttpMethods } from "./APIHandler";
import { IUsersAPIHandler } from "./interfaces/IUsersAPIHandler";

export class UsersAPIHandler extends APIHandler implements IUsersAPIHandler {

  async getUserDetailsByUsername(username: string): Promise<UserDetails> {
    const user = await APIHandler.makeRequest({
      endpoint: `/user/get/${username}`,
      token: true
    });
    if (!user) {
      throw new Error("ERROR: User not found");
    }
    return {
      id: user.id,
      username: user.nickname,
      nickname: user.name,
      email: user.email,
      avatarUrl: user.avatarUrl,
      followersCount: user.followersCount,
      followingCount: user.followingCount,
      gamesCount: user.gamesCount,
      postsCount: user.postsCount,
      reviewsCount: user.reviewsCount,
    }
  }

  async editUser(changes: UserEditRequest): Promise<void> {
    await APIHandler.makeRequest({
      endpoint: "/user/edit",
      method: HttpMethods.POST,
      token: true,
      body: changes
    })
  }

  async searchUserByNames(username: string, nickname: string): Promise<User[]> {
    return await APIHandler.makeRequest({
      endpoint: `/user/find`,
      body: { username, nickname },
      token: true
    });
  }

  async getFollowers(id: UserId): Promise<User[]> {
    return await APIHandler.makeRequest({
      endpoint: `/user/followers/${id}`,
      token: true
    });
  }

  async getFollowings(id: UserId): Promise<User[]> {
    return await APIHandler.makeRequest({
      endpoint: `/user/following/${id}`,
      token: true
    });
  }

  async getLikedGames(id: UserId): Promise<Game[]> {
    return await APIHandler.makeRequest({
      endpoint: `/user/games/${id}`,
      token: true
    });
  }

  async getPosts(id: UserId): Promise<Post[]> {
    return await APIHandler.makeRequest({
      endpoint: `/user/posts/${id}`,
      token: true
    });
  }

  async getReviews(id: UserId): Promise<Review[]> {
    return await APIHandler.makeRequest({
      endpoint: `/user/reviews/${id}`,
      token: true
    });
  }
}
