import AuthTitle from "@/components/views/shared/AuthTitle";
import Footer from "@/components/views/shared/Footer";
import RegisterContainer from "@/containers/RegisterContainer";
import { StyleSheet } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

export default function RegisterPage() {
  return (
    <SafeAreaView style={styles.container}>
      <AuthTitle/>
      <RegisterContainer />
      <Footer />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "space-between",
  },
  formContainer: {
    marginHorizontal: 30,
  },
});
