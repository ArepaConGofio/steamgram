import GameDetailsView from "@/components/pages/GameDetailsView";
import { Game } from "@/models/Game";
import { useNavigation } from "expo-router";
import { useEffect } from "react";

type Props = {
    game: Game;
};

export default function GameDetailsContainer({ game }: Props) {
    const navigation = useNavigation();

    useEffect(() => {
        navigation.setOptions({ title: game.name });
    }, [navigation, game.name])

    return <GameDetailsView game={game}/>
}