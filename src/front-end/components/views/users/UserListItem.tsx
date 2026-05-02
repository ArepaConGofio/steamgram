import { User } from "@/models/User";
import { useRouter } from "expo-router";
import { Image, StyleSheet, Text, TouchableOpacity, View } from "react-native";

type Props = {
    user: User;
}

export default function UserListItem({ user }: Props) {
    const router = useRouter();

    return (
        <TouchableOpacity style={styles.container} onPress={() => router.navigate(`/(app)/users/${user.username}`)}>
            <Image width={75} height={75} src={user.avatarUrl} style={styles.avatar}/>
            <View>
                {user.nickname && <Text style={styles.mainLabel}>{user.nickname}</Text>}
                <Text style={user.nickname ? styles.secondaryLabel : styles.mainLabel}>@{user.username}</Text>
            </View>
        </TouchableOpacity>
    )
}

const styles = StyleSheet.create({
    container: {
        flexDirection: "row",
        alignItems: "center",
        columnGap: 15
    },
    mainLabel: {
        fontWeight: "bold",
        fontSize: 16,
    },
    secondaryLabel: {
        color: "gray"
    },
    avatar: {
        borderRadius: 100,
        backgroundColor: "gray"
    }
})