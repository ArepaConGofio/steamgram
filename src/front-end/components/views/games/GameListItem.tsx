import { Game } from "@/models/Game"
import { useRouter } from "expo-router"
import { Image, StyleSheet, Text, TouchableOpacity, View } from "react-native"

type Props = {
    game: Game
}

export default function GameListItem({ game }: Props) {
    const router = useRouter();

    return (
        <TouchableOpacity style={styles.container} onPress={() => router.navigate(`/(app)/games/${game.id}`)}>
            <Image width={75} height={100} src={game.coverUrl} style={styles.cover}/>
            <View style={styles.subcontainer}>
                <Text style={styles.gameTitle}>{game.name}</Text>
                <Text style={styles.gameDeveloper}>By {game.developerName}</Text>
            </View>
        </TouchableOpacity>
    )
}

const styles = StyleSheet.create({
    container: {
        flexDirection: "row",
        flex: 1,
    },
    cover: {
        borderRadius: 10
    },
    subcontainer: {
        padding: 10,
        flex: 1
    },
    gameTitle: {
        fontSize: 16,
        fontWeight: "bold"
    },
    gameDeveloper: {
        color: "gray"
    }
})