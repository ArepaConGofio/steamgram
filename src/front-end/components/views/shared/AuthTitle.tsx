import { StyleSheet, Text } from "react-native";

export default function AuthTitle() {
  return <Text style={styles.title}>Steamgram</Text>;
}

const styles = StyleSheet.create({
  title: {
    fontSize: 24,
    textAlign: "center",
  },
});
