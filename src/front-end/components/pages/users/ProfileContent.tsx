import LoadingIndicator from "@/components/ui/LoadingIndicator";
import StaticErrorAlert from "@/components/ui/StaticAlert";
import { ContentItems } from "@/containers/ProfileContainer";
import { ProfileContentType, UserDetails } from "@/models/User";
import { Dispatch, SetStateAction } from "react";
import { StyleSheet, Text, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import ProfileCard from "./ProfileCard";
import Stats from "./Stats";

type Props = {
    user: UserDetails,
    selectContentTab: Dispatch<SetStateAction<ProfileContentType>>;
    contentTab: ProfileContentType,
    isLoading: boolean
    data: ContentItems
}

export default function ProfileContent({ user, contentTab, selectContentTab, isLoading, data }: Props) {
    function buildDataComponents(data: ContentItems) {
        if (!data) return <StaticErrorAlert message={`Error trying to get ${contentTab.toLowerCase()} items.`}/>
        switch (contentTab) {
            case "Games":
                return <Text>Games</Text>
            case "Followers":
                return <Text>Followers</Text>
            case "Following":
                return <Text>Following</Text>
            case "Posts":
                return <Text>Posts</Text>
            case "Reviews":
                return <Text>Reviews</Text>
        }
    }

    return (
        <SafeAreaView style={styles.container}>
            <ProfileCard
                username={user.username}
                nickname={user.nickname}
                avatarUrl={user.avatarUrl}
            />
            <Stats selectContent={selectContentTab}
                currentTab={contentTab}
                gamesCount={user.gamesCount}
                postsCount={user.postsCount}
                reviewsCount={user.reviewsCount}
                followersCount={user.followersCount}
                followingCount={user.followingCount}
            />
            <View style={styles.contentContainer}>
                {isLoading ? <LoadingIndicator/> : buildDataComponents(data)}
            </View>
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        paddingVertical: 10,
    },
    contentContainer: {
        backgroundColor: "#ffe7db",
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
    }
});