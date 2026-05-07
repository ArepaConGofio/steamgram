import { AuthContext } from "@/context/AuthContext";
import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review } from "@/models/Review";
import { User, UserDetails, UserId } from "@/models/User";
import { useContext } from "react";
import { APIHandler } from "./APIHandler";
import { IUsersAPIHandler } from "./interfaces/IUsersAPIHandler";

export class UsersAPIHandler extends APIHandler implements IUsersAPIHandler {
  
  async getUserDetailsByUsername(username: string): Promise<UserDetails> {
    const { token } = useContext(AuthContext);
    const user = await APIHandler.makeRequest({ 
      endpoint: `/user/get/${username}`,
      token: token
    });
    if (!user) {
      throw new Error("ERROR: User not found");
    }
    return user;
  }

  async searchUserByNames(username: string, nickname: string): Promise<User[]> {
    const { token } = useContext(AuthContext);
    return await APIHandler.makeRequest({ 
      endpoint: `/user/find`,
      body: { username, nickname },
      token: token
    });    
  }

  async getFollowers(id: UserId): Promise<User[]> {
    const { token } = useContext(AuthContext);
    return await APIHandler.makeRequest({ 
      endpoint: `/user/${id}/followers`,
      token: token
    });  
  }

  async getFollowings(id: UserId): Promise<User[]> {
    const { token } = useContext(AuthContext);
    return await APIHandler.makeRequest({ 
      endpoint: `/user/${id}/following`,
      token: token
    });  
  }
  
  async getLikedGames(id: UserId): Promise<Game[]> {
    const { token } = useContext(AuthContext);
    return await APIHandler.makeRequest({ 
      endpoint: `/user/${id}/games`,
      token: token
    });
  }

  async getPosts(id: UserId): Promise<Post[]> {
    const { token } = useContext(AuthContext);
    return await APIHandler.makeRequest({ 
      endpoint: `/user/${id}/posts`,
      token: token
    });  
  }
  
  async getReviews(id: UserId): Promise<Review[]> {
    const { token } = useContext(AuthContext);
    return await APIHandler.makeRequest({ 
      endpoint: `/user/${id}/reviews`,
      token: token
    });  
  }
}
