import { Game } from "@/models/Game"
import { useRouter } from "expo-router"
import { Image, Pressable, StyleSheet, Text, View } from "react-native"

type Props = {
    game: Game
}

export default function GameExploreResult({ game }: Props) {
    const router = useRouter();

    return (
        <Pressable style={styles.container} onPress={() => router.navigate(`/(app)/games/${game.id}`)}>
            <Image width={75} height={75} src={game.coverUrl} style={styles.cover}/>
            <View style={styles.subcontainer}>
                <Text style={styles.gameTitle}>{game.name}</Text>
                <Text style={styles.gameDeveloper}>By {game.developerName}</Text>
            </View>
        </Pressable>
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
        fontWeight: "bold"
    },
    gameDeveloper: {
        color: "gray"
    }
})