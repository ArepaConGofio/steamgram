import UserSettingsView from "@/components/pages/UserSettingsView";
import { AuthContext } from "@/context/AuthContext";
import { AuthAPIHandler } from "@/utils/AuthAPIHandler";
import { UsersAPIHandler } from "@/utils/UsersAPIHandler";
import * as LocalAuthentication from 'expo-local-authentication';
import { useNavigation } from "expo-router";
import { useContext, useEffect, useState } from "react";
import { Alert } from "react-native";

export default function UserSettingContainer() {
    const navigation = useNavigation();
    const userAPI = new UsersAPIHandler();
    const authAPI = new AuthAPIHandler();
    const { user, logout } = useContext(AuthContext);
    const [nickname, _setNickname] = useState<string | undefined>(user?.nickname);
    const [avatar, _setAvatar] = useState<string | undefined>(user?.avatarUrl);

    const setNickname = (nickname: string) => _setNickname(nickname);
    const setAvatar = (avatar: string) => _setAvatar(avatar);

    const saveChanges = async () => {
        if (!user) {
            return;
        }
        await userAPI.editUser({
            id: user.id,
            name: nickname,
            avatarUrl: avatar
        });
        Alert.alert("Saved data", "The changes was saved successfully!");
    }

    const askForDelete = () => {
        Alert.alert("Deleting account...", "Are you sure you want to delete your account?", [
            { text: "Yeah", onPress: async () => await deleteAccount() },
            { text: "Nope" }
        ])
    }

    const deleteAccount = async () => {
        if (!user) {
            return;
        }
        const canAuth = await LocalAuthentication.hasHardwareAsync();
        if (canAuth) {
            const authResult = await LocalAuthentication.authenticateAsync({
                promptDescription: "You are deleting your account, so we need to know you are, actually, you.",
            })
            if (!authResult.success) {
                return;
            }
        }
        Alert.alert("Bye bye :c", "Account deleted");
        await authAPI.deleteAccount(user.id)
        logout();
    }

    useEffect(() => {
        navigation.setOptions({ title: "Settings" })
    }, [navigation])

    return <UserSettingsView nickname={nickname} setNickname={setNickname} avatar={avatar} setAvatar={setAvatar} saveChanges={saveChanges} deleteAccount={askForDelete} />
}