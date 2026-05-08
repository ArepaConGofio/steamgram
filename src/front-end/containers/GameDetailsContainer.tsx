import GameDetailsView from "@/components/pages/GameDetailsView";
import { Game } from "@/models/Game";
import { GamesAPIHandler } from "@/utils/GamesAPIHandler";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { useNavigation } from "expo-router";
import { useEffect, useState } from "react";

type Props = {
    game: Game;
};

export type ContentTabs = "reviews"|"posts";

export default function GameDetailsContainer({ game }: Props) {
    const navigation = useNavigation();
    const [contentTab, setContentTab] = useState<ContentTabs>("posts");
    const [data, setData] = useState<unknown[]>([]);

    const generateRightHeaderButton = () => {
        // TODO: Implements saved toggle logic
        return <MaterialCommunityIcons name="plus" size={24}/>
    }

    const toggleTab = (tab: ContentTabs) => {
        setContentTab(tab == "reviews" ? "posts" : "reviews");
    }

    useEffect(() => {
        navigation.setOptions({ title: game.title, headerRight: generateRightHeaderButton });
    }, [navigation, game.title])

    useEffect(() => {
        let query;
        const api = new GamesAPIHandler();
        switch (contentTab) {
            case "reviews":
                query = api.getGameReviews;
                break;
            case "posts":
                query = api.getGamePosts;
                break;
        }
        query(game.id)
        .then(value => setData(value))
    }, [contentTab])

    return <GameDetailsView game={game} content={contentTab} goToTab={toggleTab} contentData={data}/>
}