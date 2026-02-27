import { useRouter } from "expo-router";
import { Text } from "react-native";

export default function Index() {
    const router = useRouter();
    return <Text onPress={() => router.push("/users/2")}>Community works!</Text>
}