import ExplorerView from "@/components/pages/ExplorerView";
import { GamesAPIHandler } from "@/utils/GamesAPIHandler";
import { useEffect, useState } from "react";

export type SearchType = "users" | "games";

export default function ExplorerContainer() {
    const [query, setQuery] = useState("");
    const [result, setResult] = useState<unknown[]>([]);

    const gamesApi = new GamesAPIHandler();

    const setSearchText = (text: string) => {
        setQuery(text);
    }

    const search = () => {
        setResult([]);
        gamesApi.searchGamesByTitle(query)
            .then(setResult)
            .catch(console.error)
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

    return <ExplorerView query={query} setQuery={setSearchText} onSearch={search} data={result} />
}