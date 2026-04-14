import GamesCatalogue from "@/components/pages/games/GamesCatalogue";
import { Game } from "@/models/Game";

type Props = {
    games: Game[]
}

export default function GamesContainer({ games }: Props) {
        return <GamesCatalogue games={games}/>
}