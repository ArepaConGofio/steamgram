import LoginView from "@/components/pages/Auth/LoginView";
import { AuthContext } from "@/context/AuthContext";
import { useRouter } from "expo-router";
import { useContext, useState } from "react";

export default function LoginContainer() {
  const router = useRouter();
  const { login } = useContext(AuthContext);

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const goToRegister = () => router.navigate("/register");

  const submit = async () => {
    const isLogged = await login(username, password);
    if (!isLogged) {
      alert("Invalid credentials");
      return
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
