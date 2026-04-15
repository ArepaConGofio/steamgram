import GameDetailsView from "@/components/pages/GameDetailsView";
import { Game } from "@/models/Game";
import { AntDesign } from "@expo/vector-icons";
import { useNavigation } from "expo-router";
import { useEffect } from "react";

type Props = {
    game: Game;
};

export default function GameDetailsContainer({ game }: Props) {
    const navigation = useNavigation();

    const generateRightHeaderButton = () => {
        // TODO: Implements logic of red heart when you liked it and be pressable to toggle like.
        return <AntDesign name="heart" size={24}/>
    }

    useEffect(() => {
        navigation.setOptions({ title: game.name, headerRight: generateRightHeaderButton });
    }, [navigation, game.name])

    return <GameDetailsView game={game}/>
}