import IconButton from "@/components/ui/IconButton";
import { AuthContext } from "@/context/AuthContext";
import { MaterialIcons } from "@expo/vector-icons";
import { Image } from 'expo-image';
import { Redirect, useRouter } from "expo-router";
import { useContext } from "react";
import { StyleSheet, Text, View } from "react-native";

type Props = {
  username: string;
  nickname?: string;
  avatarUrl?: string;
};

export default function ProfileCard({ username, nickname, avatarUrl }: Props) {
  const { user } = useContext(AuthContext);
  const router = useRouter();

  if (!user) return <Redirect href={"/login"} />;

  return (
    <View style={styles.container}>
      <Image source={{ uri: avatarUrl }} alt={`${username} profile photo`} style={styles.avatar} />
      <View style={styles.innerContainer}>
        {nickname && <Text style={styles.nicknameLabel}>{nickname}</Text>}
        <Text
          style={
            nickname ? styles.usernameSecondaryLabel : styles.usernameLabel
          }
        >
          @{username}
        </Text>
        <View style={styles.actionsContainer}>
          {user.username === username && (
            <IconButton
              label="Settings"
              icon={() => <MaterialIcons name="settings" size={20} />}
              callback={() => router.navigate(`/(app)/users/${user.username}/edit`)}
            />
          ) }
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
    paddingHorizontal: 10,
  },
  innerContainer: {
    paddingHorizontal: 15,
  },
  actionsContainer: {
    flex: 1,
    justifyContent: "flex-end",
    width: "100%",
  },
  usernameSecondaryLabel: {
    color: "#adadad",
    fontSize: 16,
  },
  usernameLabel: {
    fontSize: 24,
  },
  nicknameLabel: {
    fontSize: 24,
  },
  avatar: {
    backgroundColor: "gray",
    borderRadius: 100,
    width: 100,
    height: 100
  }
});
