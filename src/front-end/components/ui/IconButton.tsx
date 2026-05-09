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
        flexDirection: "row",
        alignItems: "center",
        borderColor: "#000",
        borderWidth: 1,
        height: 32,
        paddingHorizontal: 10,
        borderRadius: 10
    },
    buttonLabel: {
        fontSize: 16,
        marginLeft: 5,
    }
})