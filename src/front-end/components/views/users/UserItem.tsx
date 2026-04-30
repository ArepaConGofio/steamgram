import { User } from "@/models/User";
import { AntDesign } from "@expo/vector-icons";
import { useRouter } from "expo-router";
import { Image, Pressable, StyleSheet, Text, View } from "react-native";

type Props = {
    user: User;
}

export default function UserItem({ user }: Props) {
    const router = useRouter();


    return (
        <View style={styles.container}>
            <Pressable style={styles.leftContainer} onPress={() => router.navigate(`/(app)/users/${user.username}`)}>
                <Image width={75} height={75} src={user.avatarUrl}/>
                <View>
                    {user.nickname && <Text style={styles.mainLabel}>{user.nickname}</Text>}
                    <Text style={user.nickname ? styles.secondaryLabel : styles.mainLabel}>@{user.username}</Text>
                </View>
            </Pressable>
            <View style={styles.rightContainer}>
                <AntDesign name="heart" size={24}/>
                <AntDesign name="message" size={24}/>
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "space-between"
    },
    leftContainer: {
        flexDirection: "row",
        alignItems: "center",
        columnGap: 5
    },
    rightContainer: {
        flexDirection: "row",
        columnGap: 5
    },
    mainLabel: {
        fontSize: 16
    },
    secondaryLabel: {
        color: "gray"
    },
})