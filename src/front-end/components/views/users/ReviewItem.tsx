import { AuthContext } from "@/context/AuthContext";
import { Review } from "@/models/Review";
import { GamesAPIHandler } from "@/utils/GamesAPIHandler";
import { MaterialIcons } from "@expo/vector-icons";
import { useRouter } from "expo-router";
import { useContext, useState } from "react";
import { Alert, StyleSheet, Text, TouchableOpacity, View } from "react-native";

type Props = {
    review: Review;
    isOnProfile?: boolean
    onDelete?: () => void;
}

export default function ReviewItem({ review, isOnProfile, onDelete }: Props) {
    const router = useRouter();
    const api = new GamesAPIHandler();
    const { user } = useContext(AuthContext);
    const [isVisible, setVisible] = useState(true);

    function generateRatingStars(rating: number) {
        return [1,2,3,4,5].map(value => (
            <MaterialIcons key={value} name={value <= rating ? "star" : "star-outline"} size={24}/>
        ))
    }

    const onDeletePress = () => {
        Alert.alert("Are you sure?", "Do you want to delete this review?", [
            { text: "No" },
            { text: "Yes", onPress: deleteReview }
        ])
    }

    const deleteReview = () => {
        api.deleteReview(review.id)
        .then(_ => {
            Alert.alert("Review deleted", "The review was deleted successfully")
            setVisible(false);
            if (onDelete) {
                onDelete()
            }
        })
        .catch(console.error)
    }

    return (
        <View style={[styles.container, { display: isVisible ? "flex" : "none" }]}>
            <View style={styles.header}>
                {isOnProfile 
                ? 
                    <Text style={styles.mainLabel}>{review.gameName}</Text>
                : 
                    <View>
                        <TouchableOpacity onPress={() => router.navigate(`/(app)/users/${review.nicknameUser}`)}>
                            <Text style={styles.mainLabel}>@{review.nicknameUser}</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => router.navigate(`/(app)/games/${review.idGame}`)}>
                            <Text style={styles.gameSubtitleLabel}>{review.gameName}</Text>
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
                <TouchableOpacity onPress={onDeletePress} style={{ display: review.idUser == user?.id ? "flex" : "none" }}>
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