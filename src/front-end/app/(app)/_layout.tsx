import { AuthContext } from "@/context/AuthContext";
import ThemeProvider from "@/context/ThemeContext";
import { Redirect, Slot } from "expo-router";
import { useContext } from "react";

export default function TabLayout() {
  const { token } = useContext(AuthContext);

  if (token == null) {
    return <Redirect href={"/login"} />;
  }

  return (
    <ThemeProvider>
      <Slot />
    </ThemeProvider>
  );
}
