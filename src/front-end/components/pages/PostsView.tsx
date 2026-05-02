import { Game } from "@/models/Game";
import { Post } from "@/models/Post";
import { MaterialIcons } from "@expo/vector-icons";
import { FlatList, Image, ScrollView, Text, TouchableOpacity, View } from "react-native";
import PostItem from "../views/users/PostItem";

type Props = {
    data: Post[];
    games: Game[];
    filter: Game|undefined;
    filterSetter: React.Dispatch<React.SetStateAction<Game | undefined>>;
}

export default function PostsView({ data, games, filter, filterSetter }: Props) {  
    function renderGameItem(item: Game) {
        return (
            <TouchableOpacity onPress={() => filterSetter(filter == item ? undefined : item)}>
                <View style={[
                    { alignItems: "center", width: 125, padding: 10 },
                    filter == item && { backgroundColor: "lightgray" }
                    ]}>
                    <Image src={item.coverUrl} width={100} height={100} style={{
                        backgroundColor: "gray",
                        borderRadius: 25,
                    }}/>
                    <Text style={{ fontSize: 15, textAlign: "center" }} numberOfLines={2}>{item.name}</Text>
                </View>
            </TouchableOpacity>
        )
    }

    function renderEmptyListComponent() {
        return (
            <View>
                <Text style={{ fontSize: 18, textAlign: "center" }}>No posts found :(... Be the first!</Text>
            </View>
        )
    }
    
    return (
        <ScrollView showsVerticalScrollIndicator={false}>
            <View style={{ margin: 15, flexDirection: "row", alignItems: "center", columnGap: 15 }}>
                <MaterialIcons name="games" size={32}/>
                <Text style={{ fontSize: 24 }}>Your games!</Text>
            </View>

            <FlatList data={games}
            renderItem={({item}) => renderGameItem(item)} 
            keyExtractor={(_, index) => index.toString()}
            horizontal
            showsHorizontalScrollIndicator={false}
            contentContainerStyle={{ marginHorizontal: 15 }}
            ListFooterComponent={() => <View style={{ margin: 15 }}/>}/>

            <FlatList data={data}
            renderItem={({item}) => <PostItem post={item}/>}
            keyExtractor={(_, index) => index.toString()}
            ItemSeparatorComponent={() => <View style={{ margin: 15 }}/>}
            contentContainerStyle={{}}
            style={{ margin: 15 }} 
            scrollEnabled={false}
            ListEmptyComponent={renderEmptyListComponent}/>
        </ScrollView>
    )
}