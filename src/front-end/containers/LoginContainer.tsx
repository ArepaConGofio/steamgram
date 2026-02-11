import LoginForm from "@/components/forms/LoginForm";
import { useRouter } from "expo-router";
import { useState } from "react";

export default function LoginContainer() {
  const router = useRouter();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const goToRegister = () => router.navigate("/register");

  const submit = () => alert("Logged in! :D");

  return (
    <LoginForm
      getters={{ username, password }}
      setters={{ setUsername, setPassword }}
      callbacks={{ goToRegister, submit }}
    />
  );
}
