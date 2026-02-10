import { Link } from "expo-router";
import { Pressable, StyleSheet, Text, View } from "react-native";

export default function Navbar() {
  return (
    <View style={styles.navbar}>
      <Link href="/">
        <Pressable>
          <Text style={styles.navbarTitle}>Steamgram</Text>
        </Pressable>
      </Link>
    </View>
  );
}

const styles = StyleSheet.create({
  navbar: {
    paddingVertical: 10,
    alignItems: "center",
  },
  navbarTitle: {
    fontSize: 20,
    letterSpacing: 0.5,
    fontWeight: "bold",
  },
});
