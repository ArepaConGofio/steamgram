import { Review } from "@/models/Review";
import { AntDesign } from "@expo/vector-icons";
import { useRouter } from "expo-router";
import { StyleSheet, Text, TouchableOpacity, View } from "react-native";

type Props = {
    review: Review;
    isOnProfile?: boolean
}

export default function ReviewItem({ review, isOnProfile }: Props) {
    const router = useRouter();

    function generateRatingStars(rating: number) {
        return Array.from(Array(rating)).map((_, index) => <AntDesign key={index} name="star" size={16}/>)
    }

    return (
        <View style={styles.container}>
            <View style={styles.header}>
                {isOnProfile 
                ? 
                    <Text style={styles.mainLabel}>{review.gameTitle}</Text>
                : 
                    <View>
                        <TouchableOpacity onPress={() => router.navigate(`/(app)/users/${review.author}`)}>
                            <Text style={styles.mainLabel}>@{review.author}</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => router.navigate(`/(app)/games/${review.gameId}`)}>
                            <Text style={styles.gameSubtitleLabel}>{review.gameTitle}</Text>
                        </TouchableOpacity>
                    </View>
                }
                <View style={styles.starsContainer}>
                    {generateRatingStars(review.rating)}
                </View>
            </View>
            <Text style={styles.title}>{review.title}</Text>
            <Text>{review.description}</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
        backgroundColor: "white",
        padding: 15,
        borderRadius: 20
    },
    header: {
        flexDirection: "row",
        borderBlockColor: "black",
        borderWidth: 1,
        padding: 10,
        borderRadius: 20,
        marginBottom: 10,
        justifyContent: "space-between"
    },
    mainLabel: {
        fontSize: 14
    },
    gameSubtitleLabel: {
        fontSize: 12,
        color: "gray"
    },
    title: {
        fontSize: 16,
        fontWeight: "bold"
    },
    starsContainer: {
        flexDirection: "row",
        justifyContent: "center"
    }
})