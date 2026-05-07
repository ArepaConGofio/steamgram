import RegisterView from "@/components/pages/Auth/RegisterView";
import { AuthContext } from "@/context/AuthContext";
import { ValidationResult } from "@/services/ValidationService";
import { useRouter } from "expo-router";
import { useContext, useState } from "react";
import { Alert } from "react-native";

export default function RegisterContainer() {
  const { register } = useContext(AuthContext);
  const router = useRouter();

  const [username, setUsername] = useState("");
  const [nickname, setNickname] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [repeatedPassword, setRepeatedPassword] = useState("");

  const goToLogin = () => router.navigate("/login");

  const submit = async () => {
    if (password != repeatedPassword) {
      Alert.alert("Error", "The repeated password must be equal!");
      return;
    }
    const result = await register({ username, nickname, password, email }) as ValidationResult;
    if (!result.isValid) {
      Alert.alert("Error", result.message);
      return;
    }
    Alert.alert("Welcome!", "You are registered successfully :D")
  }

  return (
    <RegisterView
      getters={{ username, email, password, repeatedPassword, nickname }}
      setters={{ setUsername, setEmail, setPassword, setRepeatedPassword, setNickname }}
      callbacks={{ goToLogin, submit }}
    />
  );
}
