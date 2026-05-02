import LoadingIndicator from "@/components/ui/LoadingIndicator";
import StaticErrorAlert from "@/components/ui/StaticAlert";
import UserDetailsContainer from "@/containers/UserDetailsContainer";
import { UserDetails } from "@/models/User";
import { UsersAPIHandler } from "@/utils/UsersAPIHandler";
import { useLocalSearchParams } from "expo-router";
import { useEffect, useState } from "react";

export default function UserDetailsPage() {
  const { username } = useLocalSearchParams<{ username: string }>();
  const [data, setData] = useState<UserDetails>();
  const [isLoading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string>("");

  useEffect(() => {
    const api = new UsersAPIHandler();
    api.getUserDetailsByUsername(username)
      .then(value => setData(value))
      .catch(reason => setError(reason.message))
      .finally(() => setLoading(false));
  }, []);

  if (isLoading) return <LoadingIndicator category="Profile" />

  if (data === undefined || error !== "") return <StaticErrorAlert message={error} />;

  return <UserDetailsContainer user={data}/>
}