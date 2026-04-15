import LoadingIndicator from "@/components/ui/LoadingIndicator";
import StaticErrorAlert from "@/components/ui/StaticAlert";
import { ContentItems } from "@/containers/UserDetailsContainer";
import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review } from "@/models/Review";
import { ProfileContentType, User, UserDetails } from "@/models/User";
import { useNavigation } from "expo-router";
import { Dispatch, SetStateAction, useEffect } from "react";
import { FlatList, StyleSheet, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import GameItem from "../views/games/GameItem";
import PostItem from "../views/users/PostItem";
import ProfileCard from "../views/users/ProfileCard";
import ReviewItem from "../views/users/ReviewItem";
import Stats from "../views/users/Stats";
import UserItem from "../views/users/UserItem";

type Props = {
    user: UserDetails,
    selectContentTab: Dispatch<SetStateAction<ProfileContentType>>;
    contentTab: ProfileContentType,
    isLoading: boolean
    data: ContentItems
}

export default function UserDetailsView({ user, contentTab, selectContentTab, isLoading, data }: Props) {
    const navigation = useNavigation();
    
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
            case "Following":
                return <FlatList key={"users"}
                data={data as User[]}
                renderItem={({item}) => <UserItem user={item}/>}
                keyExtractor={item => item.id.toString()}
                ItemSeparatorComponent={_ => <View style={{ margin: 10 }}/>}/>
            case "Posts":
                return <FlatList key={"posts"}
                data={data as Post[]}
                renderItem={({item}) => <PostItem post={item}/>}
                keyExtractor={item => item.id.toString()}
                ItemSeparatorComponent={_ => <View style={{ margin: 10 }}/>}/>
            case "Reviews":
                return <FlatList key={"reviews"}
                data={data as Review[]}
                renderItem={({item}) => <ReviewItem review={item} isOnProfile/>}
                keyExtractor={item => item.id.toString()}
                ItemSeparatorComponent={_ => <View style={{ margin: 10 }}/>}/>
        }
    }

    useEffect(() => {
        navigation.setOptions({ title: user.username })
    }, [navigation, user.username])

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
        paddingTop: -5
    },
    contentContainer: {
        flex: 1,
        backgroundColor: "#ffe7db",
        padding: 15,
    }
});