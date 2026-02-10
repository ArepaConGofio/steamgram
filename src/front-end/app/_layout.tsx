import Footer from "@/components/shared/Footer";
import Header from "@/components/shared/Header";
import ThemeProvider from "@/context/ThemeContext";
import { Slot } from "expo-router";
import { SafeAreaView } from "react-native-safe-area-context";

export default function RootLayout() {
  return (
    <SafeAreaView>
      <ThemeProvider>
        <Header />
        <Slot />
        <Footer />
      </ThemeProvider>
    </SafeAreaView>
  );
}
