import RegisterView from "@/components/pages/Auth/RegisterView";
import { AuthContext } from "@/context/AuthContext";
import { ValidationResult } from "@/services/ValidationService";
import { useRouter } from "expo-router";
import { useContext, useState } from "react";
import { Alert } from "react-native";

export default function RegisterContainer() {
  const { register } = useContext(AuthContext);
  const router = useRouter();

  const [username, _setUsername] = useState("");
  const [nickname, setNickname] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [repeatedPassword, setRepeatedPassword] = useState("");

  const slugify = (text: string) => {
    return String(text)
      .normalize('NFKD') // split accented characters into their base characters and diacritical marks
      .replace(/[\u0300-\u036f]/g, '') // remove all the accents, which happen to be all in the \u03xx UNICODE block.
      .toLowerCase() // convert to lowercase
      .replace(/[^a-z0-9 -]/g, '') // remove non-alphanumeric characters
      .replace(/\s+/g, '-') // replace spaces with hyphens
      .replace(/-+/g, '-'); // remove consecutive hyphens
  }

  const goToLogin = () => router.navigate("/login");

  const setUsername = (username: string) => {
    const slugifiedUsername = slugify(username);
    _setUsername(slugifiedUsername);
  }

  const submit = async () => {
    if (password != repeatedPassword) {
      Alert.alert("Error", "The repeated password must be equal!");
      return;
    }
    const result = await register({ "username": username.trim(), nickname, password, email }) as ValidationResult;
    if (!result.isValid) {
      Alert.alert("Error", result.message);
      return;
    }
    Alert.alert("Welcome!", "You was registered successfully :D")
    router.replace("/");
  }

  return (
    <RegisterView
      getters={{ username, email, password, repeatedPassword, nickname }}
      setters={{ setUsername, setEmail, setPassword, setRepeatedPassword, setNickname }}
      callbacks={{ goToLogin, submit }}
    />
  );
}
