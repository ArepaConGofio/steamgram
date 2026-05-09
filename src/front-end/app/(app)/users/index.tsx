import { AuthContext } from "@/context/AuthContext";
import { Redirect } from "expo-router";
import { useContext } from "react";

export default function UsersIndex() {
  const { user } = useContext(AuthContext);
  if (user == null) {
    return <Redirect href={"/"} />;
  }
  return <Redirect href={`/(app)/users/${user.username}`} />;
}
