import { Game } from "@/models/Game";
import { ImageBackground, StyleSheet, Text } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

type Props = {
    game: Game;
}

export default function GameDetailsView({ game }: Props) {
    return (
        <SafeAreaView>
            <ImageBackground style={styles.banner} source={{ uri: game.artworkUrl }} resizeMode="cover">
                <Text style={styles.title}>{game.name}</Text>
            </ImageBackground>
        </SafeAreaView>
    );
}

const styles = StyleSheet.create({
    container: {
    },
    banner: {
        height: 300,
    },
    title: {
        backgroundColor: "#ffffff6b",
        textAlignVertical: "center",
        color: "black",
        fontSize: 24,
        padding: 20,
    }
})