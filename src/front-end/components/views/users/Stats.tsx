import { ProfileContentType } from "@/models/User";
import { StyleSheet, Text, TouchableOpacity, View } from "react-native";

type ItemProps = {
    onSelect: () => void,
    name: string,
    count: number,
    selected: boolean
}

function StatItem({ onSelect, name, count, selected }: ItemProps) {
    return (
        <TouchableOpacity onPress={onSelect} style={[styles.itemContainer, selected && styles.itemSelected]}>
            <Text style={styles.itemCount}>{count}</Text>
            <Text style={styles.itemLabel}>{name}</Text>
        </TouchableOpacity>
    )
}

type Props = {
    selectContent: (tab: ProfileContentType) => void,
    currentTab: ProfileContentType,
    gamesCount: number,
    postsCount: number,
    reviewsCount: number
}

export default function Stats({ selectContent, currentTab, gamesCount, postsCount, reviewsCount }: Props) {
    const stats = [
        { name: "Games", count: gamesCount, onSelect: () => selectContent("Games") },
        { name: "Posts", count: postsCount, onSelect: () => selectContent("Posts") },
        { name: "Reviews", count: reviewsCount, onSelect: () => selectContent("Reviews") },
    ];

    return (
        <View style={styles.container}>
            {stats.map((stat, index) => (
                <StatItem key={index} name={stat.name} count={stat.count} onSelect={stat.onSelect} selected={currentTab === stat.name} />
            ))}
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
        flexDirection: "row",
        borderColor: "#ece2e2",
    },
    itemContainer: {
        alignItems: "center",
        flex: 1,
        paddingVertical: 10,
        marginTop: 10,
    },
    itemSelected: {
        backgroundColor: "#ffe7db",
        borderRadius: "",
        color: "#ffffff"
    },
    itemLabel: {
        textDecorationLine: "underline",
    },
    itemCount: {
        fontSize: 20
    }
})