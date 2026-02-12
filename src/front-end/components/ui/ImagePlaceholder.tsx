import { View } from "react-native";

type Props = {
    width: number;
    height: number;
    color: string;
}

export default function ImagePlaceholder({ width, height, color }: Props) {
    return <View style={{
        width: width, height: height, backgroundColor: color
    }}/>
}