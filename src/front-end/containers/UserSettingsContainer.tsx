import UserSettingsView from "@/components/pages/UserSettingsView";
import { useNavigation } from "expo-router";
import { useEffect } from "react";

export default function UserSettingContainer() {
    const navigation = useNavigation();

    useEffect(() => {
        navigation.setOptions({ title: "Settings" })
    }, [navigation])

    return <UserSettingsView/>
}