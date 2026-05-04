import LoginView from "@/components/pages/Auth/LoginView";
import { AuthContext } from "@/context/AuthContext";
import { ValidationResult } from "@/services/ValidationService";
import { useRouter } from "expo-router";
import { useContext, useState } from "react";
import { Alert } from "react-native";

export default function LoginContainer() {
  const router = useRouter();
  const { login } = useContext(AuthContext);

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const goToRegister = () => router.navigate("/register");

  const submit = async () => {
    const loginResponse = await login({ username, password }) as ValidationResult;
    if (!loginResponse.isValid) {
      Alert.alert("Error", loginResponse.message)
      return;
    }
    router.replace("/");
  };

  return (
    <LoginView
      getters={{ username, password }}
      setters={{ setUsername, setPassword }}
      callbacks={{ goToRegister, submit }}
    />
  );
}
