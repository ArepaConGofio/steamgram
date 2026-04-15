import GameDetailsView from "@/components/pages/GameDetailsView";
import { Game } from "@/models/Game";

type Props = {
    game: Game;
};

export default function GameDetailsContainer({ game }: Props) {
    return <GameDetailsView game={game}/>
}