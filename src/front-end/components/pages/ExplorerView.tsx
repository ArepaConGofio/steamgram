import { SearchType } from "@/containers/ExplorerContainer";
import { Game } from "@/models/Game";
import { User } from "@/models/User";
import { MaterialIcons } from "@expo/vector-icons";
import { FlatList, Pressable, StyleSheet, Text, TextInput, View } from "react-native";
import GameExploreResult from "../views/explorer/GameExploreResult";
import UserExploreResult from "../views/explorer/UserExploreResult";

type Props = {
    query: string;
    setQuery: (query: string) => void;
    onSearch: () => void;
    data: unknown[];
    setType: (type: SearchType) => void;
    searchType: SearchType;
}

export default function ExplorerView({ query, setQuery, onSearch, data, setType, searchType }: Props) {

    const generateDataList = () => {
        return <FlatList data={data}
            renderItem={({ item }) => searchType == "games"
                ? <GameExploreResult game={item as Game} />
                : <UserExploreResult user={item as User} />
            }
            contentContainerStyle={{ marginVertical: 25 }}
            ItemSeparatorComponent={() => <View style={{ margin: 10 }} />}
            keyExtractor={(_, index) => index.toString()} />
    }

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Explore</Text>
            <Text style={styles.subtitle}>Search games or other users!</Text>
            <View style={styles.searchBox}>
                <TextInput value={query} onChangeText={setQuery} placeholder="Follow your heart! <3"
                    style={styles.searchInput} onSubmitEditing={onSearch} />
                <Pressable style={styles.searchButton} onPress={onSearch}>
                    <MaterialIcons name="search" size={20} />
                </Pressable>
            </View>
            <View style={styles.tabsContainer}>
                <Pressable style={[styles.tab, searchType == "games" && { borderBottomWidth: 3 }]} onPress={() => setType("games")}>
                    <Text style={styles.tabLabel}>Games</Text>
                </Pressable>
                <Pressable style={[styles.tab, searchType == "users" && { borderBottomWidth: 3 }]} onPress={() => setType("users")}>
                    <Text style={styles.tabLabel}>Users</Text>
                </Pressable>
            </View>
            <View>
                {generateDataList()}
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
        margin: 20,
        rowGap: 10
    },
    title: {
        fontSize: 32,
        fontWeight: "bold",
        textAlign: "center"
    },
    subtitle: {
        fontSize: 18,
        textAlign: "center"
    },
    searchBox: {
        flexDirection: "row",
        justifyContent: "space-between",
        backgroundColor: "white",
        borderBlockColor: "gray",
        borderWidth: 1,
        alignItems: "center",
        borderRadius: 10,
    },
    searchButton: {
        padding: 10,
    },
    searchInput: {
        flex: 1,
        paddingLeft: 10
    },
    tabsContainer: {
        flexDirection: "row",
        justifyContent: "space-between",
        marginTop: 10,
    },
    tab: {
        flex: 1,
        alignItems: "center",
        borderBottomColor: "black",
        borderBottomWidth: 1,
    },
    tabLabel: {
        fontSize: 18
    },
});