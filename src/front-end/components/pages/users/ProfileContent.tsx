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
    data?: any
}

export default function ProfileContent({ user, contentTab, selectContentTab, data }: Props) {
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
                <Text style={{fontSize: 24}}>You are looking for {contentTab}</Text>
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