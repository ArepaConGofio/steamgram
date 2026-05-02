import { Game } from "@/models/Game"
import { FlatList, StyleSheet, View } from "react-native"
import GameGalleryItem from "../views/games/GameGalleryItem"

type Props = {
    games: Game[]
}

export default function GamesView({ games }: Props) {
    return (
        <View style={styles.container}>
            <FlatList key={"games"}
            data={games.sort((a, b) => a.name.localeCompare(b.name))} 
            renderItem={({item}) => <GameGalleryItem game={item}/>} 
            keyExtractor={item => item.id.toString()}
            numColumns={3}
            contentContainerStyle={styles.subcontainer}/>
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