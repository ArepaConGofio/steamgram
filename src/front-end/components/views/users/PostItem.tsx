import { Post } from "@/models/Post";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { useRouter } from "expo-router";
import { StyleSheet, Text, TouchableOpacity, View } from "react-native";
import Markdown from 'react-native-markdown-display';

type Props = {
    post: Post;
}

export default function PostItem({ post }: Props) {
    const router = useRouter();

    return (
        <View style={styles.container}>
            <View style={styles.header}>
                <TouchableOpacity onPress={() => router.navigate(`/(app)/users/${post.author}`)}>
                    <Text style={styles.authorLabel}>@{post.author}</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => router.navigate(`/(app)/games/${post.gameId}`)}>
                    <Text style={styles.gameTitleLabel}>{post.gameTitle}</Text>
                </TouchableOpacity>
            </View>
            <Text style={styles.title}>{post.title}</Text>
            
            <Markdown key="pistacho" style={markdownStyle}>{post.description}</Markdown>

            <View style={styles.bottomContainer}>
                <View style={{ flexDirection: "row"}}>
                    <MaterialCommunityIcons name="heart" size={16}/>
                    <Text style={styles.likeCountLabel}>{post.likesCount}</Text>
                </View>
            </View>
        </View>
    )
}

const markdownStyle = StyleSheet.create({
    heading1: {
        fontSize: 20,
    },
    heading2: {
        fontSize: 18,
    },
    heading3: {
        fontSize: 17
    },
    heading4: {
        fontSize: 16
    },
    heading5: {
        fontSize: 15
    },
    heading6: {
        fontSize: 16
    }
})

const styles = StyleSheet.create({
    container: {
        backgroundColor: "white",
        padding: 15,
        borderRadius: 20,
    },
    header: {
        flexDirection: "row",
        justifyContent: "space-between",
        marginBottom: 15,
    },
    gameTitleLabel: {
        color: "gray",
        textDecorationLine: "underline"
    },
    authorLabel: {
        fontSize: 16,
        textDecorationLine: "underline"
    },
    title: {
        fontSize: 16,
    },
    likesCount: {
        flexDirection: "row",
        borderBlockColor: "black",
        borderWidth: 1,
        borderRadius: 20,
        padding: 5
    },
    likeCountLabel: {
        marginLeft: 5
    },
    bottomContainer: {
        flexDirection: "row",
        justifyContent: "space-between"
    }
})