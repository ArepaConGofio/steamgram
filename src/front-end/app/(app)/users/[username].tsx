import ProfileCard from "@/components/pages/users/ProfileCard";
import LoadingIndicator from "@/components/ui/LoadingIndicator";
import StaticErrorAlert from "@/components/ui/StaticAlert";
import { AuthContext } from "@/context/AuthContext";
import { UserDetails } from "@/models/User";
import { UsersAPIHandler } from "@/utils/UsersAPIHandler";
import { useLocalSearchParams } from "expo-router";
import { useContext, useEffect, useState } from "react";
import { StyleSheet } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

export default function UserProfile() {
  const { username } = useLocalSearchParams();
  const { user } = useContext(AuthContext);
  const [data, setData] = useState<UserDetails>();
  const [isLoading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string>("");

  const getUserDetails = async () => {
    UsersAPIHandler.getUserDetailsByUsername(username as string)
    .then(value => setData(undefined))
    .catch(exception => setError(exception))
    .finally(() => setLoading(false))
  }

  useEffect(() => {
    getUserDetails()
  }, []);

  if (isLoading) return <LoadingIndicator category="Profile"/>

  if (data == undefined || error != "") return <StaticErrorAlert message="No loaded"/>;

  return (
    <SafeAreaView style={styles.container}>
      <ProfileCard
        username={data.username}
        nickname={data.nickname}
        avatarUrl={data.avatarUrl}
      />  
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    paddingHorizontal: 10,
    paddingVertical: 20
  },
  title: {
    fontSize: 32,
    letterSpacing: 0.5,
  },
});
