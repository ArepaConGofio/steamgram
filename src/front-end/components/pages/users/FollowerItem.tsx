import { User } from "@/models/User";
import { AntDesign } from "@expo/vector-icons";
import { Image, StyleSheet, Text, View } from "react-native";

type Props = {
    follower: User;
}

export default function FollowerItem({ follower }: Props) {
    return (
        <View style={styles.container}>
            <View style={styles.leftContainer}>
                <Image width={75} height={75} src={follower.avatarUrl}/>
                <View>
                    {follower.nickname && <Text style={styles.mainLabel}>{follower.nickname}</Text>}
                    <Text style={follower.nickname ? styles.secondaryLabel : styles.mainLabel}>@{follower.username}</Text>
                </View>
            </View>
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