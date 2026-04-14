import { Game } from "@/models/Game"
import { FlatList, StyleSheet, View } from "react-native"
import GameItem from "./GameItem"

type Props = {
    games: Game[]
}

export default function GamesCatalogue({ games }: Props) {
    return (
        <View style={styles.container}>
            <FlatList key={"games"}
            data={games.sort((a, b) => a.name.localeCompare(b.name))} 
            renderItem={({item}) => <GameItem game={item}/>} 
            keyExtractor={item => item.id.toString()}
            numColumns={4}
            contentContainerStyle={styles.subcontainer}/>
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
        alignItems: "center",
        marginTop: 20,
        rowGap: 10
    },
    subcontainer: {
        alignItems: "stretch",
        rowGap: 20
    }
});