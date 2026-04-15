import IconButton from "@/components/ui/IconButton";
import { AuthContext } from "@/context/AuthContext";
import { FontAwesome } from "@expo/vector-icons";
import { Redirect } from "expo-router";
import { useContext } from "react";
import { Image, StyleSheet, Text, View } from "react-native";

type Props = {
  username: string;
  nickname?: string;
  avatarUrl?: string;
};

export default function ProfileCard({ username, nickname, avatarUrl }: Props) {
  const { user } = useContext(AuthContext);

  if (!user) return <Redirect href={"/login"} />;


  return (
    <View style={styles.container}>
      <Image width={100} height={100} src={avatarUrl} alt={`{username} profile photo`} />
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
          {user.username === username ? (
            <>
              <IconButton
                label="Create"
                icon={() => <FontAwesome name="plus" color="#ffffff" />}
                callback={() => alert("Creating something")}
              />
              <IconButton
                label="Edit"
                icon={() => <FontAwesome name="edit" color="#ffffff" />}
                callback={() => alert("Editing profile")}
              />
            </>
          ) : (
            <IconButton
              label="Follow"
              icon={() => <FontAwesome name="user" color="#ffffff" />}
              callback={() => alert("Following person")}
            />
          )}
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
    flexDirection: "row",
    flex: 1,
    justifyContent: "center",
    alignItems: "flex-end",
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
});
