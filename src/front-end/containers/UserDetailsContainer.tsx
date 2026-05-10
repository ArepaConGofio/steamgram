import UserDetailsView from "@/components/pages/UserDetailsView";
import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review } from "@/models/Review";
import { ProfileContentType, User, UserDetails } from "@/models/User";
import { UsersAPIHandler } from "@/utils/UsersAPIHandler";
import { useNavigation } from "expo-router";
import { useEffect, useState } from "react";

type Props = {
    user: UserDetails
}

export type ContentItems = Game[] | User[] | Post[] | Review[] | null;

export default function UserDetailsContainer({ user }: Props) {
    const navigation = useNavigation();
    const [currentTab, setCurrentTab] = useState<ProfileContentType>("Games");
    const [content, setContent] = useState<ContentItems>(null);
    const [isLoading, setLoading] = useState(true);

    async function loadDataFromCategory() {
        const api = new UsersAPIHandler();
        let query;
        switch (currentTab) {
            case "Games":
                query = api.getLikedGames(user.id);
                break;
            case "Followers":
                query = api.getFollowers(user.id);
                break;
            case "Following":
                query = api.getFollowings(user.id);
                break;
            case "Posts":
                query = api.getPosts(user.id);
                break;
            case "Reviews":
                query = api.getReviews(user.id);
                break;
        }
        return await query;
    }

    useEffect(() => {
        navigation.setOptions({ title: user.username })
    }, [navigation, user.username])

    useEffect(() => {
        setLoading(true);
        setContent(null);
        loadDataFromCategory()
        .then(value => {
            setContent(value);
        })
        .catch(reason => {
            console.error(reason);
            setContent(null);
        })
        .finally(() => setLoading(false));
    }, [currentTab])

    return <UserDetailsView user={user} selectContentTab={setCurrentTab} contentTab={currentTab} data={content} isLoading={isLoading}/>
}