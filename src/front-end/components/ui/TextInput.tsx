import { StyleSheet, Text, TextInput, View } from "react-native";

type Props = {
    value: string;
    setter: React.Dispatch<React.SetStateAction<string>>;
    label: string;
    placeholder?: string;
}

export default function LabeledTextInput({
    value, setter, label, placeholder
}: Props) {
    return <View style={styles.inputContainer}>
        <Text style={styles.inputLabel}>{label}</Text>
        <TextInput style={styles.input} value={value} onChangeText={setter} placeholder={placeholder}/>
    </View>
}

const styles = StyleSheet.create({
    input: {
        borderColor: "#D9C4BF",
        borderWidth: 1,
        borderRadius: 10,
        marginVertical: 5
    },
    inputLabel: {
        fontWeight: "bold"
    },
    inputContainer: {
        marginVertical: 8
    },
    errorLabel: {
        color: "red"
    }
})