import LoadingIndicator from "@/components/ui/LoadingIndicator";
import StaticErrorAlert from "@/components/ui/StaticAlert";
import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { Review } from "@/models/Review";
import { ContentType, UserDetails } from "@/models/User";
import { Dispatch, SetStateAction } from "react";
import { FlatList, StyleSheet, Text, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import GameListItem from "../views/games/GameListItem";
import PostItem from "../views/users/PostItem";
import ProfileCard from "../views/users/ProfileCard";
import ReviewItem from "../views/users/ReviewItem";
import Stats from "../views/users/Stats";

type Props = {
    user: UserDetails,
    selectContentTab: Dispatch<SetStateAction<ContentType>>;
    contentTab: ContentType,
    isLoading: boolean
    data: unknown[]
}

export default function UserDetailsView({ user, contentTab, selectContentTab, isLoading, data }: Props) {

    /**
     * Construye el listado de contenido según el tipo de contenido
     * que se desea mirar.
     * @param data - Items a generar.
     * @returns Un FlatList con los elementos a mostrar. 
     */
    function buildDataComponents(data: unknown[]) {
        if (!data) return <StaticErrorAlert message={`Error trying to get ${contentTab.toLowerCase()} items.`} />
        switch (contentTab) {
            case "Games":
                return <FlatList key={"games"}
                    data={data as Game[]}
                    renderItem={({ item }) => <GameListItem game={item} />}
                    ItemSeparatorComponent={_ => <View style={{ margin: 10 }} />}
                    ListEmptyComponent={() => <Text style={{
                        textAlign: "center",
                        fontSize: 16
                    }}>No games available</Text>}
                    keyExtractor={item => item.id.toString()} />
            case "Posts":
                return <FlatList key={"posts"}
                    data={data as Post[]}
                    renderItem={({ item }) => <PostItem post={item} />}
                    keyExtractor={item => item.id.toString()}
                    ListEmptyComponent={() => <Text style={{
                        textAlign: "center",
                        fontSize: 16
                    }}>No posts available</Text>}
                    ItemSeparatorComponent={_ => <View style={{ margin: 10 }} />} />
            case "Reviews":
                return <FlatList key={"reviews"}
                    data={data as Review[]}
                    renderItem={({ item }) => <ReviewItem review={item} isOnProfile />}
                    keyExtractor={item => item.id.toString()}
                    ListEmptyComponent={() => <Text style={{
                        textAlign: "center",
                        fontSize: 16
                    }}>No reviews available</Text>}
                    ItemSeparatorComponent={_ => <View style={{ margin: 10 }} />} />
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
            />
            <View style={styles.contentContainer}>
                {isLoading ? <LoadingIndicator /> : buildDataComponents(data)}
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