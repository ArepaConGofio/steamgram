import { AuthContext } from "@/context/AuthContext";
import { Review } from "@/models/Review";
import { GamesAPIHandler } from "@/utils/GamesAPIHandler";
import { MaterialIcons } from "@expo/vector-icons";
import { useRouter } from "expo-router";
import { useContext } from "react";
import { Alert, StyleSheet, Text, TouchableOpacity, View } from "react-native";

type Props = {
    review: Review;
    isOnProfile?: boolean
}

export default function ReviewItem({ review, isOnProfile }: Props) {
    const router = useRouter();
    const api = new GamesAPIHandler();
    const { user } = useContext(AuthContext);

    function generateRatingStars(rating: number) {
        return [1,2,3,4,5].map(value => (
            <MaterialIcons key={value} name={value <= rating ? "star" : "star-outline"} size={24}/>
        ))
    }

    const onDeletePress = () => {
        // TODO: Implement review deleting logic
        Alert.alert("Deleting review")
        /*
        api.deleteReview(review.id)
        .then(value => Alert.alert(value ? "Review deleted" : "Error deleting review"))
        .catch(reason => Alert.alert("Error", reason))
        */
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

            </View>
            <Text style={styles.title}>{review.title}</Text>
            <Text>{review.description}</Text>
            <View style={{ flexDirection: "row", justifyContent: "space-between" }}>
                <View style={styles.starsContainer}>
                    {generateRatingStars(review.rating)}
                </View>
                <TouchableOpacity onPress={onDeletePress} style={{ display: review.userId == user?.id ? "flex" : "none" }}>
                    <MaterialIcons name="delete" size={24}/>
                </TouchableOpacity>
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
        backgroundColor: "white",
        padding: 15,
        borderRadius: 20,
        rowGap: 10
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
    }
})