import { Linking, StyleSheet, Text } from "react-native";

export default function Footer() {
  return (
    <Text style={styles.copyrightLabel}>
      <Text style={styles.businessTitle} onPress={() => Linking.openURL("https://github.com/strSalazar-JesusLugo2002")}>ArepaConGofio</Text> © 2026
    </Text>
  );
}

const styles = StyleSheet.create({
  copyrightLabel: {
    letterSpacing: 1,
    textAlign: "center"
  },
  businessTitle: {
    textDecorationLine: "underline"
  }
});
