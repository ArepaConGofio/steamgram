import { SearchType } from "@/containers/ExplorerContainer";
import { Game } from "@/models/Game";
import { User } from "@/models/User";
import { MaterialIcons } from "@expo/vector-icons";
import { FlatList, Pressable, ScrollView, StyleSheet, Text, TextInput, View } from "react-native";
import GameListItem from "../views/games/GameListItem";
import UserListItem from "../views/users/UserListItem";

type Props = {
    query: string;
    setQuery: (query: string) => void;
    onSearch: () => void;
    data: unknown[];
    setType: (type: SearchType) => void;
    searchType: SearchType;
}

export default function ExplorerView({ query, setQuery, onSearch, data, setType, searchType }: Props) {

    const _renderEmptyListComponent = () => {
        return <Text style={{
            textAlign: "center", fontSize: 18
        }}>No results. Search something or reload!</Text>
    }

    const generateDataList = () => {
        return <FlatList data={data}
            renderItem={({ item }) => searchType == "games"
                ? <GameListItem game={item as Game} />
                : <UserListItem user={item as User} />
            }
            ItemSeparatorComponent={() => <View style={{ margin: 10 }} />}
            contentContainerStyle={{ marginVertical: 20 }}
            keyExtractor={(_, index) => index.toString()} 
            scrollEnabled={false}
            ListEmptyComponent={_renderEmptyListComponent}
            />
    }

    return (
        <ScrollView style={styles.container}>
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
                { generateDataList() }
            </View>
        </ScrollView>
    )
}

const styles = StyleSheet.create({
    container: {
        margin: 20,
        rowGap: 10,
    },
    title: {
        fontSize: 32,
        fontWeight: "bold",
        textAlign: "center",
        marginBottom: 10,
    },
    subtitle: {
        fontSize: 18,
        textAlign: "center",
        marginBottom: 10,
    },
    searchBox: {
        marginBottom: 10,
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