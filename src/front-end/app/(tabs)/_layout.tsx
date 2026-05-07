import { default as MyHeader } from "@/components/views/shared/Header";
import ThemeProvider from "@/context/ThemeContext";
import { MaterialIcons } from "@expo/vector-icons";
import { Tabs, useRouter } from "expo-router";
import * as SecureStore from 'expo-secure-store';
import { useEffect } from "react";

export default function TabLayout() {
  const router = useRouter();

  useEffect(() => {
    SecureStore.getItemAsync("token")
      .then(value => {
        if (value == null) {
          router.replace("/login")
        }
      });
  }, []);

  return (
    <ThemeProvider>
      <Tabs>
        <Tabs.Screen
          name="index"
          options={{
            title: "Community",
            header: () => <MyHeader title="Community" />,
            tabBarIcon: () => <MaterialIcons size={24} name="home" />,
          }}
        />
        <Tabs.Screen
          name="explore"
          options={{
            title: "Explore",
            header: () => <MyHeader title="Explore" />,
            tabBarIcon: () => <MaterialIcons size={24} name="search" />,
          }}
        />
        <Tabs.Screen
          name="games"
          options={{
            title: "Games",
            header: () => <MyHeader title="Games" />,
            tabBarIcon: () => <MaterialIcons size={24} name="gamepad" />,
          }}
        />
      </Tabs>
    </ThemeProvider>
  );
}
