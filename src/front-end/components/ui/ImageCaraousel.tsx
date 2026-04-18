import { FlatList, Image, ImageStyle, StyleSheet, useWindowDimensions, View } from "react-native";

type Props = {
    urls: string[];
    width?: number;
    height?: number;
    spacing?: number;
    imageStyle?: ImageStyle
}

export default function ImageCarousel({ urls, width, height, spacing, imageStyle }: Props) {
    const screenSize = useWindowDimensions();
    const fixedWidth = width ? width : screenSize.width;
    const fixedHeigth = height ? height : screenSize.height / 3;
    
    return (
        <FlatList data={urls} 
        renderItem={({item, index}) => (
            <View style={[{ 
                width: fixedWidth, 
                height: fixedHeigth, 
                marginHorizontal: spacing ? spacing : 5 }]} 
                  key={index}>
                <Image source={{ uri: item }} width={fixedWidth} height={fixedHeigth} style={imageStyle} />
            </View>
        )}
        horizontal
        />
    )
}

const styles = StyleSheet.create({
    spacing: {
        marginHorizontal: 5
    }
})