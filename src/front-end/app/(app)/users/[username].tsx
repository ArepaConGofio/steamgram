import { AuthContext } from "@/context/AuthContext";
import { useLocalSearchParams } from "expo-router";
import { useContext } from "react";
import { Text } from "react-native";

export default function UserProfile() {
    const { username } = useLocalSearchParams();
    const { user } = useContext(AuthContext);

    return <Text>Welcome {username}! Your steam id is: {user?.steamId}</Text>
}