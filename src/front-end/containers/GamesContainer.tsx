import GamesView from "@/components/pages/GamesView";
import { Game } from "@/models/Game";

type Props = {
    games: Game[]
}

export default function GamesContainer({ games }: Props) {
    return <GamesView games={games}/>
}