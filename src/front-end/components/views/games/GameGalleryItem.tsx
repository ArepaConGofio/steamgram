import { Game } from "@/models/Game";
import { useRouter } from "expo-router";
import { Image, StyleSheet, Text, TouchableOpacity, useWindowDimensions, View } from "react-native";

type Props = {
    game: Game
}

export default function GameGalleryItem({ game }: Props) {
    const router = useRouter()
    const width = useWindowDimensions().width / 4;

    return (
        <TouchableOpacity onPress={() => router.push(`/(app)/games/${game.id}`)}>
            <View style={[styles.container, {width: width}]} >
                <Image src={game.banner} alt={game.id + " cover"} width={75} height={100} style={styles.image} />
                <Text style={styles.title}>{game.title}</Text>
            </View>
        </TouchableOpacity>
    )
}

const styles = StyleSheet.create({
    container: {
        alignItems: "center",
        marginHorizontal: 5,
    },
    title: {
        fontSize: 16,
        textAlign: "center"
    },
    image: {
        resizeMode: "cover",
        borderRadius: 10,
        elevation: 3,
    }
})