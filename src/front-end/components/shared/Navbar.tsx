import { StyleSheet, Text, View } from "react-native";

export default function Navbar() {
    return <View style={styles.navbar}>
        <Text style={styles.navbarTitle}>Steamgram</Text>
        <Text>Login</Text>
    </View>
}

const styles = StyleSheet.create({
    navbar: {
        marginHorizontal: 10,
        flexDirection: "row",
        justifyContent: "space-between",
        alignItems: "center"
    },
    navbarTitle: {
        fontSize: 20,
        fontWeight: "bold"
    }
})