import { StyleSheet, Text, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

type Props = {
    title?: string;
    message: string;
}

export default function StaticErrorAlert({ title, message }: Props) {
    if (!title) title = "Something happend!"

    return (
        <SafeAreaView style={styles.container}>
            <View style={styles.subcontainer}>
                <Text style={styles.title}>{title}</Text>
                <Text>{message}</Text>
            </View>
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: "center",
        margin: 50
    },
    subcontainer: {
        backgroundColor: "#ffacac",
        borderWidth: 1,
        borderColor: "#e95656",
        borderRadius: 10,
        flex: 0.3,
        padding: 10
    },
    title: {
        fontSize: 16,
        fontWeight: "bold",
        borderBottomWidth: 1,
        borderColor: "#e95656",
        paddingVertical: 5,
        marginBottom: 5
    }
})