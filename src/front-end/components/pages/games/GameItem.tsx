import { Game } from "@/models/Game";
import { useRouter } from "expo-router";
import { Image, Pressable, StyleSheet, Text, View } from "react-native";

type Props = {
    game: Game
}

export default function GameItem({ game }: Props) {
    const router = useRouter()

    return (
        <Pressable onPress={() => router.push(`/(app)/games/${game.id}`)}>
            <View style={styles.container} >
                <Image src={game.coverUrl} alt={game.id + " cover"} width={75} height={100} style={styles.image} />
                <Text style={styles.title}>{game.name}</Text>
            </View>
        </Pressable>
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