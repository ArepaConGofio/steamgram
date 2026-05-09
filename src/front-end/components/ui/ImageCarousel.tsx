import { FlatList, Image, ImageStyle, useWindowDimensions, View } from "react-native";
import ImagePlaceholder from "./ImagePlaceholder";

type Props = {
    urls: string[];
    width?: number;
    height?: number;
    spacing?: number;
    imageStyle?: ImageStyle
    showPlaceholder?: boolean
}

export default function ImageCarousel({ urls, width, height, spacing, imageStyle, showPlaceholder }: Props) {
    const screenSize = useWindowDimensions();
    const fixedWidth = width ? width : screenSize.width;
    const fixedHeigth = height ? height : screenSize.height / 3;
    
    return (
        <FlatList data={urls} 
        renderItem={({item, index}) => (
            <View style={[{ 
                width: fixedWidth, 
                height: fixedHeigth
            }]} 
                  key={index}>
                <Image source={{ uri: item }} width={fixedWidth} height={fixedHeigth} style={imageStyle} />
            </View>
        )}
        horizontal
        ItemSeparatorComponent={_ => <View style={{ margin: spacing ? spacing : 5}}/>}
        ListEmptyComponent={_ => (
            showPlaceholder && <ImagePlaceholder width={fixedWidth} height={fixedHeigth} color="gray"/>
        )}
        />
    )
}