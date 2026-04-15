import LoadingIndicator from "@/components/ui/LoadingIndicator";
import StaticErrorAlert from "@/components/ui/StaticAlert";
import GameDetailsContainer from "@/containers/GameDetailsContainer";
import { Game } from "@/models/Game";
import { GamesAPIHandler } from "@/utils/GamesAPIHandler";
import { useLocalSearchParams } from "expo-router";
import { useEffect, useState } from "react";

export default function GameDetailsPage() {
    const { gameId } = useLocalSearchParams<{ gameId: string }>();
    const [data, setData] = useState<Game>();
    const [isLoading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string>("");

    useEffect(() => {
        const api = new GamesAPIHandler();
        api.getGameDetails(parseInt(gameId))
            .then(value => setData(value))
            .catch(reason => setError(reason.message))
            .finally(() => setLoading(false));
    }, []);

    if (isLoading) return <LoadingIndicator category="Profile" />

    if (data === undefined || error !== "") return <StaticErrorAlert message={error} />;

    return <GameDetailsContainer game={data}/>
}