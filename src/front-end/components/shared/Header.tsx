import { FontAwesome } from "@expo/vector-icons";
import { Header } from "@react-navigation/elements";
import { useRouter } from "expo-router";

function LeftCreateButton() {
  return (
    <FontAwesome name="plus" size={24} style={{ paddingHorizontal: 20 }} />
  );
}

function RightUserButton() {
  const router = useRouter();
  return (
    <FontAwesome
      name="user"
      size={24}
      style={{ paddingHorizontal: 20 }}
      onPress={() => router.push("/(app)/users")}
    />
  );
}

export default function generateHeader(title: string) {
  return (
    <Header
      title={title}
      headerShadowVisible={false}
      headerTitleAlign="center"
      headerLeft={(props) => LeftCreateButton()}
      headerRight={(props) => RightUserButton()}
    />
  );
}