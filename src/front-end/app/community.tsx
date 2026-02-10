import { AuthContext } from "@/context/AuthContext";
import { Redirect } from "expo-router";
import { useContext } from "react";
import { Text } from "react-native";

export default function Community() {
  const auth = useContext(AuthContext);

  if (auth.token == null) {
    return <Redirect href={"/login"}></Redirect>;
  }

  return <Text>Community works!</Text>;
}
