import PostsView from "@/components/pages/PostsView"
import { Game } from "@/models/Game"
import { Post } from "@/models/Post"
import { useState } from "react"

type Props = {
    posts: Post[]
    games: Game[]
}

export default function PostsContainer({ posts, games }: Props) {
    const [filter, setFilter] = useState<Game>();

    let filteredPosts = posts;
    if (filter) {
        filteredPosts = posts.filter(post => post.gameId === filter.id);
    }

    return <PostsView data={filteredPosts} games={games} filterSetter={setFilter} filter={filter}/>
}