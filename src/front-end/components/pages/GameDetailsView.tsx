import { Game } from "@/models/Game";
import { Text } from "react-native";

type Props = {
    game: Game;
}

export default function GameDetailsView({ game }: Props) {
    return <Text>{game.name}</Text>
}