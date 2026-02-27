import generateHeader from "@/components/shared/Header";
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
        <Tabs.Screen
          name="index"
          options={{
            href: null,
          }}
        />
        <Tabs.Screen
          name="community/index"
          options={{
            title: "Community",
            header: () => generateHeader("Steamgram"),
            tabBarIcon: () => <FontAwesome size={24} name="home" />,
          }}
        />
        <Tabs.Screen
          name="explore/index"
          options={{
            title: "Explore",
            header: () => generateHeader("Explore"),
            tabBarIcon: () => <FontAwesome size={24} name="search" />,
          }}
        />
        <Tabs.Screen
          name="games/index"
          options={{
            title: "Games",
            header: () => generateHeader("Games"),
            tabBarIcon: () => <FontAwesome size={24} name="gamepad" />,
          }}
        />
        <Tabs.Screen
          name="users"
          options={{
            href: null,
            headerShown: false,
          }}
        />
      </Tabs>
    </ThemeProvider>
  );
}
