import { Game } from "@/models/Game";
import { Image, ImageStyle, ScrollView, StyleSheet, Text, useWindowDimensions, View } from "react-native";
import ImageCarousel from "../ui/ImageCarousel";

type Props = {
    game: Game;
}


const GENRE_COLORS = [
    "#F5A1C3", "#A2BDF4", "#43ca8b", "#FAD062", "#fa9b64"
];

export default function GameDetailsView({ game }: Props) {

    const SCREEN_WIDTH = useWindowDimensions().width;
    let screenshotsWidth = SCREEN_WIDTH;
    let screenshotsStyle: ImageStyle = { borderRadius: 20 }
    if (game.screenshots.length > 1) {
        screenshotsWidth = SCREEN_WIDTH / 1.2;
    }
    const coverSize = SCREEN_WIDTH / 3;

    const randomGenreColor = () => GENRE_COLORS[Math.floor(Math.random() * GENRE_COLORS.length)]

    return (
        <View style={styles.container}>
            <ScrollView>
                <View style={styles.header}>
                    <Image source={{ uri: game.coverUrl }} style={[styles.cover, { height: coverSize, width: coverSize }]} />
                    <View style={[styles.headerSubcontainer, { width: coverSize * 2 }]}>
                        <Text style={styles.title}>{game.name}</Text>
                        <Text style={styles.developerLabel}>By {game.developerName}</Text>
                    </View>
                </View>
                <Text style={styles.description}>{game.description}</Text>
                <Text style={styles.subtitle}>Genres</Text>
                <View style={styles.badgeContainer}>
                    {game.genres.map((genre, index) => (
                        <Text key={index} style={[styles.badge, { backgroundColor: randomGenreColor() }]}>{genre}</Text>
                    ))}
                </View>
                <Text style={styles.subtitle}>Platforms</Text>
                <View style={[styles.badgeContainer, { width: SCREEN_WIDTH }]}>
                    {game.platforms.map((platform, index) => (
                        <Text key={index} style={[styles.badge, { borderColor: "black", borderWidth: 1 }]}>{platform}</Text>
                    ))}
                </View>
                <Text style={styles.subtitle}>Gallery</Text>
                <ImageCarousel urls={game.screenshots} width={screenshotsWidth} imageStyle={screenshotsStyle} showPlaceholder />
                
            </ScrollView>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1
    },
    header: {
        flexDirection: "row",
        padding: 10
    },
    headerSubcontainer: {
        paddingHorizontal: 15,
        paddingTop: 10
    },
    title: {
        fontSize: 24,
        fontWeight: "bold",
    },
    developerLabel: {
        color: "gray",
        fontWeight: "bold"
    },
    cover: {
        borderRadius: 20,
    },
    description: {
        letterSpacing: 0.5,
        fontSize: 14,
        textAlign: "justify",
        padding: 15
    },
    badgeContainer: {
        paddingHorizontal: 15,
        paddingBottom: 15,
        flexDirection: "row",
        columnGap: 10,
        rowGap: 5,
        flexWrap: "wrap"
    },
    subtitle: {
        fontSize: 18,
        fontWeight: "bold",
        paddingHorizontal: 15,
        paddingBottom: 5
    },
    badge: {
        borderRadius: 10,
        padding: 5,
        paddingHorizontal: 10,
    },
})