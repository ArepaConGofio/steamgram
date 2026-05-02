import { AuthContext } from "@/context/AuthContext";
import { Post } from "@/models/Post";
import { PostsAPIHandler } from "@/utils/PostsAPIHandler";
import { MaterialCommunityIcons, MaterialIcons } from "@expo/vector-icons";
import { useRouter } from "expo-router";
import { useContext } from "react";
import { Alert, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import Markdown from 'react-native-markdown-display';

type Props = {
    post: Post;
}

export default function PostItem({ post }: Props) {
    const router = useRouter();
    const api = new PostsAPIHandler();
    const { user } = useContext(AuthContext);

    const onAuthorPress = () => router.navigate(`/(app)/users/${post.author}`);
    const onGamePress = () => router.navigate(`/(app)/games/${post.gameId}`);

    const onDeletePress = () => {
        // TODO: Implement post deleting logic
        if (!user) return;
        Alert.alert("Deleting post")
        /*
        api.deletePost(user?.id, post.id)
        .then(value => Alert.alert(value ? "Post deleted" : "Error deleting post"))
        .catch(reason => Alert.alert("Error", reason))
        */
    };

    return (
        <View style={styles.container}>
            <View style={styles.header}>
                <View style={styles.headerLeft}>
                    <TouchableOpacity onPress={onAuthorPress}>
                        <Text style={styles.authorLabel}>@{post.author}</Text>
                    </TouchableOpacity>
                    {/* TODO: Implement post creation date */}
                    <Text style={{ color: "gray" }}>{post.creationDate ? post.creationDate : "01-01-2000"}</Text>
                </View>
                <View style={styles.headerRight}>
                    <TouchableOpacity onPress={onGamePress}>
                        <Text style={styles.gameTitleLabel}>Game: {post.gameTitle}</Text>
                    </TouchableOpacity>
                </View>
            </View>
            <Text style={styles.title}>{post.title}</Text>
            
            <Markdown style={markdownStyle}>{post.description}</Markdown>

            <View style={styles.bottomContainer}>
                <View style={{ flexDirection: "row", alignItems: "center" }}>
                    <MaterialCommunityIcons name="heart" size={24}/>
                    <Text style={styles.likeCountLabel}>{post.likesCount}</Text>
                </View>
                <TouchableOpacity onPress={onDeletePress} style={{ display: post.userId == user?.id ? "flex" : "none" }}>
                    <MaterialIcons name="delete" size={24}/>
                </TouchableOpacity>
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
    headerLeft: {
        
    },
    headerRight: {
        justifyContent: "center"
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