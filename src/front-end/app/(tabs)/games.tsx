import LoadingIndicator from "@/components/ui/LoadingIndicator";
import StaticErrorAlert from "@/components/ui/StaticAlert";
import GamesContainer from "@/containers/GamesContainer";
import { Game } from "@/models/Game";
import { GamesAPIHandler } from "@/utils/GamesAPIHandler";
import { useEffect, useState } from "react";

export default function GamesPage() {
    const [data, setData] = useState<Game[]>([]);
    const [isLoading, setLoading] = useState(true);
    const [error, setError] = useState<string>("");

    const fetchGames = () => {
        const api = new GamesAPIHandler();
        api.getAllGames()
        .then(value => setData(value))
        .catch(reason => setError(reason.message))
        .finally(() => setLoading(false))
    }

    useEffect(() => {
        fetchGames()
    }, [])

    if (isLoading) return <LoadingIndicator category="Games" />

      if (data === undefined || error !== "") return <StaticErrorAlert message={error} />;

    return <GamesContainer games={data}/>
}