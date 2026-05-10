import LoadingIndicator from "@/components/ui/LoadingIndicator";
import GamesContainer from "@/containers/GamesContainer";
import { Game } from "@/models/Game";
import { GamesAPIHandler } from "@/utils/GamesAPIHandler";
import { useFocusEffect } from "expo-router";
import { useState } from "react";

export default function GamesPage() {
    const [data, setData] = useState<Game[]>([]);
    const [isLoading, setLoading] = useState(true);

    useFocusEffect(() => {
        const api = new GamesAPIHandler();
        api.getAllGames()
            .then(setData)
            .catch(console.error)
            .finally(() => setLoading(false))
    })

    if (isLoading) return <LoadingIndicator category="Games" />

    return <GamesContainer games={data} />
}