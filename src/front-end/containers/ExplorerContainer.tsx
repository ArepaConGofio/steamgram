import ExplorerView from "@/components/pages/ExplorerView";
import { Game } from "@/models/Game";
import { User } from "@/models/User";
import { GamesAPIHandler } from "@/utils/GamesAPIHandler";
import { UsersAPIHandler } from "@/utils/UsersAPIHandler";
import { useEffect, useState } from "react";
import { Alert } from "react-native";

export type SearchType = "users" | "games";

export default function ExplorerContainer() {
    const [query, setQuery] = useState("");
    const [queryType, setQueryType] = useState<SearchType>("games");
    const [result, setResult] = useState<Game[] | User[]>([]);

    const gamesApi = new GamesAPIHandler();
    const usersApi = new UsersAPIHandler();

    const setSearchText = (text: string) => {
        setQuery(text);
    }

    const setSearchType = (type: SearchType) => {
        setQueryType(type);
    }

    const search = () => {
        setResult([]);

        if (queryType == "games") {
            gamesApi.searchGamesByTitle(query)
                .then(result => setResult(result))
                .catch(reason => Alert.alert("Error", reason))
        } else {
            usersApi.searchUserByUsername(query)
                .then(result => setResult(...[result]))
                .catch(reason => Alert.alert("Error", reason))
            usersApi.searchUserByNickname(query)
                .then(result => setResult(...[result]))
                .catch(reason => Alert.alert("Error", reason))

        }
    }

    useEffect(() => {
        let length = query.length;
        if (length == 0) {
            setResult([]);
            return;
        }
        if (length > 3) {
            search();
        }
    }, [query]);

    useEffect(() => {
        setResult([]);
    }, [queryType]);

    return <ExplorerView query={query} setQuery={setSearchText} onSearch={search} data={result} setType={setSearchType} searchType={queryType} />
}