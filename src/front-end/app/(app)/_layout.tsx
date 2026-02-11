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
        <Tabs.Screen name="index" options={{ title: "Community", tabBarIcon: () => <FontAwesome size={24} name="gamepad"/> }}/>
        <Tabs.Screen name="users" options={{ title: "My Profile", tabBarIcon: () => <FontAwesome size={24} name="user"/> }}/>
        <Tabs.Screen name="explore" options={{ title: "Explore", tabBarIcon: () => <FontAwesome size={24} name="search"/> }}/>
      </Tabs>
    </ThemeProvider>
  );
}
