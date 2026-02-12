import { StyleSheet, Text, TouchableOpacity } from "react-native";

type Props = {
    label: string;
    icon: () => React.ReactNode;
    callback: () => void;
}

export default function IconButton({ label, icon, callback }: Props) {
    return (
        <TouchableOpacity onPress={callback} style={styles.button}>
            {icon()}
            <Text style={styles.buttonLabel}>{label}</Text>            
        </TouchableOpacity>
    )
}

const styles = StyleSheet.create({
    button: {
        flex: 1,
        backgroundColor: "#115554",
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "center",
        borderColor: "#ffffff",
        height: 32
    },
    buttonLabel: {
        fontSize: 16,
        marginLeft: 5,
        color: "#fff"
    }
})