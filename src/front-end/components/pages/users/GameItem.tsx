import { Game } from "@/models/Game";
import { Image, StyleSheet, Text, View } from "react-native";

type Props = {
    game: Game
}

export default function GameItem({ game }: Props) {
    return (
        <View style={styles.container}>
            <Image src={game.coverUrl} alt={game.id + " cover"} width={75} height={100} style={styles.image} />
            <Text style={styles.title}>{game.name}</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
        alignItems: "center",
    },
    title: {
        fontSize: 16
    },
    image: {
        resizeMode: "cover",
        marginHorizontal: 10
    }
})