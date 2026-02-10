import Footer from "@/components/shared/Footer";
import Header from "@/components/shared/Header";
import AuthProvider from "@/context/AuthContext";
import ThemeProvider from "@/context/ThemeContext";
import { Slot } from "expo-router";
import { SafeAreaView } from "react-native-safe-area-context";

export default function RootLayout() {
  return (
    <SafeAreaView>
      <AuthProvider>
        <ThemeProvider>
          <Header />
          <Slot />
          <Footer />
        </ThemeProvider>
      </AuthProvider>
    </SafeAreaView>
  );
}
