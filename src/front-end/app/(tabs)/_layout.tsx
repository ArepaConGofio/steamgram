import { default as MyHeader } from "@/components/views/shared/Header";
import { AuthContext } from "@/context/AuthContext";
import ThemeProvider from "@/context/ThemeContext";
import { MaterialIcons } from "@expo/vector-icons";
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
            title: "Community",
            header: () => <MyHeader title="Community"/>,
            tabBarIcon: () => <MaterialIcons size={24} name="home" />,
          }}
        />
        <Tabs.Screen
          name="explore"
          options={{
            title: "Explore",
            header: () => <MyHeader title="Explore"/>,
            tabBarIcon: () => <MaterialIcons size={24} name="search" />,
          }}
        />
        <Tabs.Screen
          name="games"
          options={{
            title: "Games",
            header: () => <MyHeader title="Games"/>,
            tabBarIcon: () => <MaterialIcons size={24} name="gamepad" />,
          }}
        />
      </Tabs>
    </ThemeProvider>
  );
}
