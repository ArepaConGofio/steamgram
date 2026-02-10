import { Link } from "expo-router";
import { Pressable, StyleSheet, Text, View } from "react-native";

export default function Header() {
  return (
    <View style={styles.header}>
      <Link href="/">
        <Pressable>
          <Text style={styles.headerTitle}>Steamgram</Text>
        </Pressable>
      </Link>
    </View>
  );
}

const styles = StyleSheet.create({
  header: {
    paddingVertical: 10,
    alignItems: "center",
  },
  headerTitle: {
    fontSize: 20,
    letterSpacing: 0.5,
    fontWeight: "bold",
  },
});
