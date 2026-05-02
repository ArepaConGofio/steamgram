import { AuthContext } from "@/context/AuthContext";
import { MaterialIcons } from "@expo/vector-icons";
import { Header } from "@react-navigation/elements";
import { useRouter } from "expo-router";
import { useContext, useState } from "react";
import { StyleSheet, Text, TouchableOpacity, View } from "react-native";

type Props = {
  title: string;
}


export default function MyHeader({ title }: Props) {
  const router = useRouter();
  const { logout } = useContext(AuthContext);
  const [menuVisible, setMenuVisible] = useState<boolean>(false);

  const onLogoutPress = () => {
    setMenuVisible(false);
    logout()
  }

  const onUserProfilePress = () => {
    setMenuVisible(false);
    router.navigate("/(app)/users/")
  }

  return (
    <View style={styles.container}>
      <Header
        title={title}
        headerShadowVisible={false}
        headerTitleAlign="left"
        headerRight={(props) => (
          <MaterialIcons
            name="menu"
            size={24}
            style={{ padding: 20 }}
            onPress={() => setMenuVisible(!menuVisible)}
          />
        )}
      />
      <View style={[styles.actions, { display: menuVisible ? "flex" : "none" }]}>
        <TouchableOpacity style={styles.action} onPress={onUserProfilePress}>
          <Text style={{ fontWeight: "bold" }}>My profile</Text>
          <MaterialIcons name="person" size={24} />
        </TouchableOpacity>
        <TouchableOpacity style={styles.action} onPress={onLogoutPress}>
          <Text style={{ fontWeight: "bold" }}>Log out</Text>
          <MaterialIcons name="logout" size={24} />
        </TouchableOpacity>
      </View>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: "white"
  },
  actions: {
    padding: 20,
    paddingTop: 0,
    rowGap: 15
  },
  action: {
    flexDirection: "row",
    justifyContent: "flex-end",
    alignItems: "center",
    columnGap: 5,
  }
});