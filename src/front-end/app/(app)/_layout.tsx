import { AuthContext } from "@/context/AuthContext";
import ThemeProvider from "@/context/ThemeContext";
import { FontAwesome } from "@expo/vector-icons";
import { Redirect, Tabs } from "expo-router";
import { useContext } from "react";

export default function TabLayout() {
  const { token } = useContext(AuthContext);

  if (token == null) {
    return <Redirect href={"/login"} />;
  }

  return (
    <ThemeProvider>
      <Tabs>
        <Tabs.Screen name="index" options={{ title: "Home", tabBarIcon: () => <FontAwesome size={28} name="home"/> }}/>
        <Tabs.Screen name="explore" options={{ title: "Explore", tabBarIcon: () => <FontAwesome size={28} name="search"/> }}/>
      </Tabs>
    </ThemeProvider>
  );
}
