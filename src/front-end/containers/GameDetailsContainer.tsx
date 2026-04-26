import GameDetailsView from "@/components/pages/GameDetailsView";
import { Game } from "@/models/Game";
import { AntDesign } from "@expo/vector-icons";
import { useNavigation } from "expo-router";
import { useEffect, useState } from "react";

type Props = {
    game: Game;
};

export type ContentTabs = "reviews"|"posts";

export default function GameDetailsContainer({ game }: Props) {
    const navigation = useNavigation();
    const [contentTab, setContentTab] = useState<ContentTabs>("reviews");

    const generateRightHeaderButton = () => {
        // TODO: Implements logic of red heart when you liked it and be pressable to toggle like.
        return <AntDesign name="heart" size={24}/>
    }

    const toggleTab = (tab: ContentTabs) => {
        setContentTab(tab == "reviews" ? "posts" : "reviews");
    }

    useEffect(() => {
        navigation.setOptions({ title: game.name, headerRight: generateRightHeaderButton });
    }, [navigation, game.name])

    return <GameDetailsView game={game} content={contentTab} goToTab={toggleTab}/>
}