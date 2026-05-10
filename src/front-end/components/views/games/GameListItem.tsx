import { Game } from "@/models/Game"
import { useRouter } from "expo-router"
import { Image, StyleSheet, Text, TouchableOpacity, View } from "react-native"

type Props = {
    game: Game
    inExplorer?: boolean
}

export default function GameListItem({ game, inExplorer }: Props) {
    const router = useRouter();

    if (inExplorer) {
        return (
            <TouchableOpacity style={styles.container} onPress={() => router.navigate(`/(app)/games/igdb/${game.idIgdb}`)}>
                <Image width={75} height={100} src={game.banner} style={styles.cover} />
                <View style={styles.subcontainer}>
                    <Text style={styles.gameTitle}>{game.title}</Text>
                    <Text style={styles.gameDeveloper}>By {game.developerName}</Text>
                </View>
            </TouchableOpacity>
        )
    }

    return (
        <TouchableOpacity style={styles.container} onPress={() => router.navigate(`/(app)/games/${game.id}`)}>
            <Image width={75} height={100} src={game.banner} style={styles.cover} />
            <View style={styles.subcontainer}>
                <Text style={styles.gameTitle}>{game.title}</Text>
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
        borderRadius: 10,
        elevation: 3,
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