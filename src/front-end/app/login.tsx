import { useRouter } from "expo-router";
import { Text } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

export default function Login() {
    const router = useRouter();

    return <SafeAreaView>
        <Text onPress={() => router.navigate("/register")}>To register</Text>
        <Text>Login works!</Text>
    </SafeAreaView>
}