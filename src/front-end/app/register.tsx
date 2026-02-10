import Footer from "@/components/shared/Footer";
import LabeledTextInput from "@/components/ui/TextInput";
import { AuthContext } from "@/context/AuthContext";
import { globalStyles } from "@/styles/GlobalStyles";
import { useRouter } from "expo-router";
import { useContext, useState } from "react";
import { StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

export default function Register() {
  const { register } = useContext(AuthContext);
  const router = useRouter();

  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [repeatedPassword, setRepeatedPassword] = useState("");

  return (
    <SafeAreaView style={styles.container}>
      <Text style={styles.title}>Steamgram</Text>
      <View style={styles.formContainer}>
        <Text style={globalStyles.title}>Register</Text>
        <View style={styles.form}>
          <LabeledTextInput
            label="Username"
            setter={setUsername}
            value={username}
          />
          <LabeledTextInput label="Email" setter={setEmail} value={email} />
          <LabeledTextInput
            label="Password"
            setter={setPassword}
            value={password}
          />
          <LabeledTextInput
            label="Repeat password"
            setter={setRepeatedPassword}
            value={repeatedPassword}
          />
          <TouchableOpacity style={styles.formSubmit}>
            <Text style={styles.formSubmitLabel}>Register</Text>
          </TouchableOpacity>
        </View>
        <Text
          onPress={() => router.navigate("/login")}
          style={styles.toLoginText}
        >
          Already have an account? Log in!
        </Text>
      </View>
      <Footer />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  title: {
    fontSize: 24,
    textAlign: "center"
  },
  container: {
    flex: 1,
    justifyContent: "space-between",
  },
  formContainer: {
    marginHorizontal: 30,
  },
  form: {
    borderWidth: 1,
    borderColor: "#D9C4BF",
    borderRadius: 20,
    padding: 25,
    marginVertical: 10,
  },
  formSubmit: {
    marginTop: 10,
    backgroundColor: "#013440",
    borderRadius: 10,
    padding: 10,
  },
  formSubmitLabel: {
    color: "#ffffff",
    textAlign: "center",
    fontWeight: "bold",
  },
  toLoginText: {
    color: "#5C82F2",
    textDecorationLine: "underline",
  },
});
