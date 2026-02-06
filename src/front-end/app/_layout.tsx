import Header from "@/components/shared/Header";
import { Slot } from "expo-router";
import { SafeAreaView } from "react-native-safe-area-context";

export default function RootLayout() {
  return (
    <SafeAreaView>
      <Header />
      <Slot />
    </SafeAreaView>
  );
}
