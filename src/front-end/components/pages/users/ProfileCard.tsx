import { Image, StyleSheet, Text, View } from "react-native";

type Props = {
  username: string;
  nickname?: string;
  avatarUrl?: string;
};

export default function ProfileCard({ username, nickname, avatarUrl }: Props) {
  const avatar = avatarUrl ? avatarUrl : "assets/images/icon.png";

  return (
    <View style={styles.container}>
      <Image src={avatar} width={200} height={200} />
      <View>
        {nickname && <Text style={styles.nicknameLabel}>{nickname}</Text>}
        <Text
          style={
            nickname ? styles.usernameSecondaryLabel : styles.usernameLabel
          }
        >
          {username}
        </Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {},
  usernameSecondaryLabel: {
    color: "#adadad",
    fontSize: 12,
  },
  usernameLabel: {
    fontSize: 24,
  },
  nicknameLabel: {
    fontSize: 24,
  },
});
