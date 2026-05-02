import { User } from "@/models/User";
import { useRouter } from "expo-router";
import { Image, Pressable, StyleSheet, Text, View } from "react-native";

type Props = {
    user: User
}

export default function UserExploreResult({ user }: Props) {
    const router = useRouter();

    const generateLabels = () => {
        if (user.nickname) {
            return (
                <>
                    <Text style={styles.mainLabel}>{user.nickname}</Text>
                    <Text style={styles.secondaryLabel}>@{user.username}</Text>
                </>
            )
        }
        return <Text style={styles.mainLabel}>@{user.username}</Text>
    }
 
    return (
        <Pressable style={styles.container} onPress={() => router.navigate(`/(app)/users/${user.username}`)}>
            <Image src={user.avatarUrl} width={75} height={75} style={styles.avatar}/>
            <View style={styles.subcontainer}>
                {generateLabels()}
            </View>
        </Pressable>
    )
}

const styles = StyleSheet.create({
    container: {
        flexDirection: "row"
    },
    avatar: {
        borderRadius: 100,
        backgroundColor: "gray"
    },
    subcontainer: {
        padding: 10
    },
    mainLabel: {
        fontWeight: "bold",
        fontSize: 18
    },
    secondaryLabel: {
        color: "gray"
    }
})