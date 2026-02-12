import ProfileCard from "@/components/pages/users/ProfileCard";
import { AuthContext } from "@/context/AuthContext";
import { User } from "@/models/User";
import { Redirect, useLocalSearchParams } from "expo-router";
import { useContext, useEffect, useState } from "react";
import { StyleSheet } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

export default function UserProfile() {
  const { username } = useLocalSearchParams();
  const { user } = useContext(AuthContext);
  const [targetUser, setTargetUser] = useState<User | null>(null);

  useEffect(() => {
    if (username === user?.username) {
      setTargetUser({
        id: user.id,
        username: user.username,
        nickname: user.nickname,
        email: user.email,
      });
    } else {
      setTargetUser({
        id: 1,
        username: "Zekken2002",
        nickname: "ElJesus",
        email: "example@example.com",
      });
    }
  }, [user, username]);

  if (targetUser == null) {
    return <Redirect href={"/login"} />;
  }

  return (
    <SafeAreaView style={styles.container}>
      <ProfileCard
        username={targetUser.username}
        nickname={targetUser.nickname}
        avatarUrl={targetUser.avatarUrl}
      />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 10,
  },
  title: {
    fontSize: 32,
    letterSpacing: 0.5,
  },
});
