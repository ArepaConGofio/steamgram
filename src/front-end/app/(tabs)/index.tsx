import LoadingIndicator from "@/components/ui/LoadingIndicator";
import PostsContainer from "@/containers/PostsContainer";
import { AuthContext } from "@/context/AuthContext";
import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { PostsAPIHandler } from "@/utils/PostsAPIHandler";
import { UsersAPIHandler } from "@/utils/UsersAPIHandler";
import { useFocusEffect } from "expo-router";
import { useContext, useState } from "react";

export default function CommunityPage() {
  const { user } = useContext(AuthContext);
  const [posts, setPosts] = useState<Post[]>([]);
  const [games, setGames] = useState<Game[]>([]);
  const [isLoadingPosts, setLoadingPosts] = useState(true);
  const [isLoadingGames, setLoadingGames] = useState(true);

  useFocusEffect(() => {
    const api = new PostsAPIHandler();
    api.getAllPosts()
      .then(setPosts)
      .catch(console.error)
      .finally(() => setLoadingPosts(false))

    if (user == null) return;
    const userApi = new UsersAPIHandler();
    userApi.getLikedGames(user?.id)
      .then(setGames)
      .catch(console.error)
      .finally(() => setLoadingGames(false))
  });

  if (isLoadingPosts || isLoadingGames) return <LoadingIndicator category="Posts" />

  return <PostsContainer posts={posts} games={games} />
}
