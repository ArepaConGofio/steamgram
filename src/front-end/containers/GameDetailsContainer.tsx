import GameDetailsView from "@/components/pages/GameDetailsView";
import { AuthContext } from "@/context/AuthContext";
import { Game } from "@/models/Game";
import { GamesAPIHandler } from "@/utils/GamesAPIHandler";
import { UsersAPIHandler } from "@/utils/UsersAPIHandler";
import { useNavigation } from "expo-router";
import { useContext, useEffect, useState } from "react";

type Props = {
    game: Game;
};

export type ContentTabs = "reviews"|"posts";

export default function GameDetailsContainer({ game }: Props) {
    const navigation = useNavigation();
    const gamesAPI = new GamesAPIHandler();
    const { user } = useContext(AuthContext);
    const [contentTab, setContentTab] = useState<ContentTabs>("posts");
    const [data, setData] = useState<unknown[]>([]);
    const [isSaved, setSaved] = useState(false);

    const onSavePress = async () => {
        if (!user) return;
        try {
            const changed = await gamesAPI.saveGameIntoLibrary(game.id, user.id)
            if (changed) {
                setSaved(!isSaved);
            }
        } catch (error) {
            console.error(error);
        }
    }

    const fetchData = () => {
        let query;
        switch (contentTab) {
            case "reviews":
                query = gamesAPI.getGameReviews;
                break;
            case "posts":
                query = gamesAPI.getGamePosts;
                break;
        }
        query(game.id)
        .then(value => setData(value))
    }

    const toggleTab = (tab: ContentTabs) => {
        setData([]);
        setContentTab(tab == "reviews" ? "posts" : "reviews");
    }

    useEffect(() => {
        navigation.setOptions({ title: game.title });
    }, [navigation, game.title])

    useEffect(() => {
        fetchData();
    }, [contentTab])

    useEffect(() => {
        if (!user) {
            return;
        }
        const api = new UsersAPIHandler();
        api.getLikedGames(user.id)
        .then(games => setSaved(games.find(g => g.id == game.id) != undefined))
        .catch(console.error)
    }, [user, isSaved])

    return <GameDetailsView game={game} content={contentTab} goToTab={toggleTab} contentData={data} isSaved={isSaved} onSavePress={onSavePress} updateData={fetchData}/>
}