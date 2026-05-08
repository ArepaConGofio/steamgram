import LoadingIndicator from "@/components/ui/LoadingIndicator";
import GamesContainer from "@/containers/GamesContainer";
import { AuthContext } from "@/context/AuthContext";
import { Game } from "@/models/Game";
import { GamesAPIHandler } from "@/utils/GamesAPIHandler";
import { useContext, useEffect, useState } from "react";

export default function GamesPage() {
    const { user } = useContext(AuthContext);
    const [data, setData] = useState<Game[]>([]);
    const [isLoading, setLoading] = useState(true);
    const [error, setError] = useState<string>("");

    useEffect(() => {
        /*
        if (!user) {
            setError("User not load correctly!");
            setLoading(false);
            return;
        }
        const api = new UsersAPIHandler();
        api.getLikedGames(user?.id)
        .then(value => setData(value))
        .catch(reason => setError(reason.message))
        .finally(() => setLoading(false))
        */

        const api = new GamesAPIHandler();
        api.getAllGames()
        .then(setData)
        .catch(console.error)
        .finally(() => setLoading(false))
    }, [])

    if (isLoading) return <LoadingIndicator category="Games" />

    return <GamesContainer games={data}/>
}