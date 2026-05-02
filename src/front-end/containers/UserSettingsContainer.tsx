import UserSettingsView from "@/components/pages/UserSettingsView";
import { AuthContext } from "@/context/AuthContext";
import { useNavigation } from "expo-router";
import { useContext, useEffect, useState } from "react";
import { Alert } from "react-native";

export default function UserSettingContainer() {
    const navigation = useNavigation();
    const { user } = useContext(AuthContext);
    const [nickname, _setNickname] = useState<string|undefined>(user?.nickname);
    const [avatar, _setAvatar] = useState<string|undefined>(user?.avatarUrl);

    const setNickname = (nickname: string) => _setNickname(nickname);
    const setAvatar = (avatar: string) => _setAvatar(avatar);

    const saveChanges = () => {
        Alert.alert("Changes saved", `Yes, is saved`)
    }

    useEffect(() => {
        navigation.setOptions({ title: "Settings" })
    }, [navigation])

    return <UserSettingsView nickname={nickname} setNickname={setNickname} avatar={avatar} setAvatar={setAvatar} saveChanges={saveChanges}/>
}