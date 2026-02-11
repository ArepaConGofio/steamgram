import Footer from "@/components/shared/Footer";
import Header from "@/components/shared/Header";
import RegisterContainer from "@/containers/RegisterContainer";
import { StyleSheet } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

export default function Register() {
  return (
    <SafeAreaView style={styles.container}>
      <Header/>
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
