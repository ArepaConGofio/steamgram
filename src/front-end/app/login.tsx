import Header from "@/components/shared/AuthTitle";
import Footer from "@/components/shared/Footer";
import LoginContainer from "@/containers/LoginContainer";
import { StyleSheet } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

export default function Login() {
  return (
    <SafeAreaView style={styles.container}>
      <Header/>
      <LoginContainer />
      <Footer />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "space-between",
  },
  title: {
    fontSize: 24,
    textAlign: "center",
  },
});
