import UserDetailsView from "@/components/pages/UserDetailsView";
import { ContentType, UserDetails } from "@/models/User";
import { UsersAPIHandler } from "@/utils/UsersAPIHandler";
import { useNavigation } from "expo-router";
import { useEffect, useState } from "react";

type Props = {
    user: UserDetails
}

export default function UserDetailsContainer({ user }: Props) {
    const navigation = useNavigation();
    const [currentTab, setCurrentTab] = useState<ContentType>("Games");
    const [content, setContent] = useState<unknown[]>([]);
    const [isLoading, setLoading] = useState(true);

    async function loadDataFromCategory() {
        const api = new UsersAPIHandler();
        let query;
        switch (currentTab) {
            case "Games":
                query = api.getLikedGames(user.id);
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
        setContent([]);
        loadDataFromCategory()
        .then(setContent)
        .catch(reason => {
            console.error(reason);
            setContent([]);
        })
        .finally(() => setLoading(false));
    }, [currentTab])

    return <UserDetailsView user={user} selectContentTab={setCurrentTab} contentTab={currentTab} data={content} isLoading={isLoading}/>
}