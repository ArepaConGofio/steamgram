import LoadingIndicator from "@/components/ui/LoadingIndicator";
import StaticErrorAlert from "@/components/ui/StaticAlert";
import { Game } from "@/models/Game";
import { GamesAPIHandler } from "@/utils/GamesAPIHandler";
import { useLocalSearchParams } from "expo-router";
import { useEffect, useState } from "react";
import { Text } from "react-native";

export default function GameDetail() {
    const { gameId } = useLocalSearchParams();
    const [data, setData] = useState<Game>();
    const [isLoading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string>("");

    const getUserDetails = async () => {
        const api = new GamesAPIHandler();
        api.getGameDetails(parseInt(gameId as string))
            .then(value => setData(value))
            .catch(reason => setError(reason.message))
            .finally(() => setLoading(false));
    }

    useEffect(() => {
    getUserDetails()
    }, []);

    if (isLoading) return <LoadingIndicator category="Profile" />

    if (data === undefined || error !== "") return <StaticErrorAlert message={error} />;

    return <Text>{data.id}</Text>
}