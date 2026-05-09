import { ActivityIndicator, StyleSheet, Text } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

type Props = {
    category?: "Profile"|"Game"|"Post"|"Games"|"Posts"
}

export default function LoadingIndicator({ category }: Props) {
    return (
        <SafeAreaView style={styles.container}>
            <Text style={styles.label}>Loading {category && category.toLowerCase()}</Text>
            <ActivityIndicator size="large"/>
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
    },
    label: {
        marginBottom: 30,
        fontSize: 24,
        fontWeight: "bold"
    }
})