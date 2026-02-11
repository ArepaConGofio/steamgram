import RegisterForm from "@/components/forms/RegisterForm";
import { useRouter } from "expo-router";
import { useState } from "react";

export default function RegisterContainer() {
  const router = useRouter();

  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [repeatedPassword, setRepeatedPassword] = useState("");

  const goToLogin = () => router.navigate("/login");

  const submit = () => alert("Account created! :D");

  return (
    <RegisterForm
      getters={{ username, email, password, repeatedPassword }}
      setters={{ setUsername, setEmail, setPassword, setRepeatedPassword }}
      callbacks={{ goToLogin, submit }}
    />
  );
}
