import { useRouter } from "expo-router";
import { StyleSheet, Text, View } from "react-native";

export default function Navbar() {
  const router = useRouter();

  return (
    <View style={styles.navbar}>
      <Text style={styles.navbarTitle} onPress={() => router.navigate("/")}>
        Steamgram
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  navbar: {
    marginHorizontal: 10,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  navbarTitle: {
    fontSize: 20,
    fontWeight: "bold",
  },
});
