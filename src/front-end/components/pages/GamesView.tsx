import { Game } from "@/models/Game"
import { FlatList, StyleSheet, Text, View } from "react-native"
import GameGalleryItem from "../views/games/GameGalleryItem"

type Props = {
    games: Game[]
}

export default function GamesView({ games }: Props) {
    const _renderEmptyComponent = () => {
        return <Text style={{
            textAlign: "center", fontWeight: "bold", fontSize: 18
        }}>No games saved. Look your favourites in the explorer! :D</Text>
    }

    return (
        <View style={styles.container}>
            <FlatList key={"games"}
            data={games.sort((a, b) => a.title.localeCompare(b.title))} 
            renderItem={({item}) => <GameGalleryItem game={item}/>} 
            keyExtractor={item => item.id.toString()}
            numColumns={3}
            contentContainerStyle={styles.subcontainer}
            ListEmptyComponent={_renderEmptyComponent}/>
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
        alignItems: "center",
        marginTop: 20,
    },
    subcontainer: {
        alignItems: "flex-start",
        rowGap: 20,
    }
});