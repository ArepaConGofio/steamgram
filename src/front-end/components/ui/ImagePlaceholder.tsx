import { StyleSheet, Text, View } from "react-native";

type Props = {
    width: number;
    height: number;
    color: string;
}

export default function ImagePlaceholder({ width, height, color }: Props) {
    return <View style={[{
        width: width, height: height, backgroundColor: color
    }, styles.container]}>
        <Text style={styles.label}>Hi, i'm a fucking placeholder</Text>
    </View>
}

const styles = StyleSheet.create({
    container: {
        justifyContent: "center",
        alignItems: "center"
    },
    label: {
        color: "white",
        fontSize: 15
    }
})