import LoadingIndicator from "@/components/ui/LoadingIndicator";
import StaticErrorAlert from "@/components/ui/StaticAlert";
import { ContentItems } from "@/containers/ProfileContainer";
import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { ProfileContentType, UserDetails } from "@/models/User";
import { Dispatch, SetStateAction } from "react";
import { FlatList, StyleSheet, Text, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import GameItem from "./GameItem";
import PostItem from "./PostItem";
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
    /**
     * Construye el listado de contenido según el tipo de contenido
     * que se desea mirar.
     * @param data - Items a generar.
     * @returns Un FlatList con los elementos a mostrar. 
     */
    function buildDataComponents(data: ContentItems) {
        if (!data) return <StaticErrorAlert message={`Error trying to get ${contentTab.toLowerCase()} items.`}/>
        switch (contentTab) {
            case "Games":
                return <FlatList key={"games"}
                data={data as Game[]} 
                renderItem={({item}) => <GameItem game={item}/>} 
                keyExtractor={item => item.id.toString()}
                numColumns={3}
                contentContainerStyle={{ alignItems: "center", rowGap: 20 }}/>
            case "Followers":
                return <Text>Followers</Text>
            case "Following":
                return <Text>Following</Text>
            case "Posts":
                return <FlatList key={"posts"}
                data={data as Post[]}
                renderItem={({item}) => <PostItem post={item}/>}
                keyExtractor={item => item.id.toString()}
                ItemSeparatorComponent={_ => <View style={{ margin: 10 }}/>}/>
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
        flex: 1,
        backgroundColor: "#ffe7db",
        padding: 15,
    }
});