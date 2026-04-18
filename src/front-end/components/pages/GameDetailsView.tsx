import { Game } from "@/models/Game";
import { ImageStyle, StyleSheet, Text, useWindowDimensions, View } from "react-native";
import ImageCarousel from "../ui/ImageCarousel";

type Props = {
    game: Game;
}

export default function GameDetailsView({ game }: Props) {
    const screenWidth = useWindowDimensions().width;
    let screenshotsWidth = screenWidth;
    let screenshotsStyle: ImageStyle = { borderRadius: 20 }
    if (game.screenshots.length > 1) {
        screenshotsWidth = screenWidth / 1.2;
    } 
    
    
    return (
        <View style={styles.container}>
            <ImageCarousel urls={game.screenshots} width={screenshotsWidth} imageStyle={screenshotsStyle} showPlaceholder/>
            <View style={styles.descriptionContainer}>
                <Text style={styles.descriptionLabel}>Description</Text>
                <Text style={styles.description}>{game.description}</Text>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        
    },
    title: {
        backgroundColor: "#ffffff9a",
        textAlignVertical: "center",
        color: "black",
        fontSize: 24,
        fontWeight: "bold",
        padding: 20,
    },
    statsContainer: {
        borderColor: "black",
        borderWidth: 1,
        borderRadius: 10,
        margin: 10,
        padding: 10,
    },
    descriptionContainer: {
        padding: 10,
        borderColor: "black",
        borderWidth: 1,
        margin: 10,
        borderRadius: 10,
        rowGap: 10
    },
    descriptionLabel: {
        fontSize: 20,
        fontWeight: "bold"
    },
    description: {
        letterSpacing: 0.5,
        fontSize: 14,
        textAlign: "justify"
    }
})