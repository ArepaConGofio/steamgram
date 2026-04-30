import LoadingIndicator from "@/components/ui/LoadingIndicator";
import StaticErrorAlert from "@/components/ui/StaticAlert";
import PostsContainer from "@/containers/PostsContainer";
import { AuthContext } from "@/context/AuthContext";
import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { PostsAPIHandler } from "@/utils/PostsAPIHandler";
import { UsersAPIHandler } from "@/utils/UsersAPIHandler";
import { useContext, useEffect, useState } from "react";

export default function CommunityPage() {
  const { user } = useContext(AuthContext);
  const [posts, setPosts] = useState<Post[]>([]);
  const [games, setGames] = useState<Game[]>([]);
  const [isLoadingPosts, setLoadingPosts] = useState(true);
  const [isLoadingGames, setLoadingGames] = useState(true);
  const [error, setError] = useState<string>("");

  useEffect(() => {
    const api = new PostsAPIHandler();
    api.getAllPosts()
      .then(value => setPosts(value))
      .catch(reason => setError(reason.message))
      .finally(() => setLoadingPosts(false))

    if (user == null) return;
    const userApi = new UsersAPIHandler();
    userApi.getLikedGames(user?.id)
      .then(value => setGames(value))
      .catch(reason => setError(reason.message))
      .finally(() => setLoadingGames(false))
  }, [])

  if (isLoadingPosts || isLoadingGames) return <LoadingIndicator category="Posts" />

  if (posts === undefined || error !== "" || games === undefined) return <StaticErrorAlert message={error} />;

  return <PostsContainer posts={posts} games={games} />
}
