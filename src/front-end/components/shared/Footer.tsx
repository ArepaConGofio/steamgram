import { usePathname } from "expo-router";
import { StyleSheet, Text, View } from "react-native";
import Navbar from "./Navbar";

export default function Footer() {
  const pathname = usePathname();

  if (pathname.includes("login") || pathname.includes("register")) {
    return (
      <View style={styles.footer}>
        <Text style={styles.copyrightLabel}>ArepaConGofio © 2026</Text>
      </View>
    );
  }

  return <Navbar />;
}

const styles = StyleSheet.create({
  footer: {
    position: "fixed",
    bottom: 0,
    flex: 1,
    width: "100%",
    paddingVertical: 10,
    alignItems: "center",
  },
  copyrightLabel: {
    letterSpacing: 1,
  },
});
