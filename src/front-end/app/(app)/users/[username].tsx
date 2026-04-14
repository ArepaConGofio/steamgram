import LoadingIndicator from "@/components/ui/LoadingIndicator";
import StaticErrorAlert from "@/components/ui/StaticAlert";
import ProfileContainer from "@/containers/ProfileContainer";
import { UserDetails } from "@/models/User";
import { UsersAPIHandler } from "@/utils/UsersAPIHandler";
import { useLocalSearchParams } from "expo-router";
import { useEffect, useState } from "react";

export default function UserProfile() {
  const { username } = useLocalSearchParams();
  const [data, setData] = useState<UserDetails>();
  const [isLoading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string>("");

  const getUserDetails = async () => {
    const api = new UsersAPIHandler();
    api.getUserDetailsByUsername(username as string)
      .then(value => setData(value))
      .catch(reason => setError(reason.message))
      .finally(() => setLoading(false));
  }

  useEffect(() => {
    getUserDetails()
  }, []);

  if (isLoading) return <LoadingIndicator category="Profile" />

  if (data === undefined || error !== "") return <StaticErrorAlert message={error} />;

  return <ProfileContainer user={data}/>
}


