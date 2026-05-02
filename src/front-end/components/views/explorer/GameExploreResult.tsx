import { Game } from "@/models/Game"
import { Text } from "react-native"

type Props = {
    game: Game
}

export default function GameExploreResult({ game }: Props) {
    return (
        <Text>{game.name}</Text>
    )
}