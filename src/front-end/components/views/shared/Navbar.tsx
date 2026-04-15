import { FontAwesome, Ionicons } from "@expo/vector-icons";
import { StyleSheet, Text, View } from "react-native";
import Button from "../ui/Button";

export default function Navbar() {
  return (
    <View style={styles.navbar}>
      <Button onPress={() => alert("To community page")}>
        <FontAwesome name="gamepad" size={32} />
        <Text>Community</Text>
      </Button>
      <Button onPress={() => alert("To create modal")}>
        <Ionicons name="add" size={32} />
        <Text>Create</Text>
      </Button>
      <Button onPress={() => alert("To explore page")}>
        <Ionicons name="search" size={24} />
        <Text>Explore</Text>
      </Button>
    </View>
  );
}

const styles = StyleSheet.create({
  navbar: {
    position: "fixed",
    bottom: 0,
    paddingVertical: 10,
    flex: 1,
    width: "100%",
    flexDirection: "row",
    justifyContent: "space-around",
  },
});
