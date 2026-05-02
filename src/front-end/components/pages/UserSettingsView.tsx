import { AuthContext } from "@/context/AuthContext";
import { MaterialIcons } from "@expo/vector-icons";
import { useContext } from "react";
import { Image, StyleSheet, Text, TextInput, TouchableOpacity, View } from "react-native";

export default function UserSettingsView() {
    const { user } = useContext(AuthContext);

    return (
        <View style={styles.container}>
            <View style={styles.subcontainer}>
                <Text style={styles.optionTitle}>Change nickname</Text>
                <TextInput placeholder="Be wonderful, be you!" style={styles.optionInput} />
            </View>

            <View style={styles.subcontainer}>
                <Text style={styles.optionTitle}>Change your profile avatar</Text>
                <Image src={user?.avatarUrl} width={100} height={100} style={styles.avatar}/>
                <TextInput placeholder="Insert the image url" style={styles.optionInput} />
                <Text style={{ color: "gray", textAlign: "center" }}>To add an image, you need to upload an url from web.</Text>
            </View>

            <TouchableOpacity style={[styles.button, { borderColor: "green" }]}>
                <MaterialIcons name="save" size={24} />
                <Text>Save changes</Text>
            </TouchableOpacity>

            <View style={[styles.subcontainer, styles.dangerZone]}>
                <Text style={styles.optionTitle}>Delete your account</Text>
                <Text>This will remove all your data from the server.</Text>
                <TouchableOpacity style={[styles.button, { borderColor: "red" }]}>
                    <MaterialIcons name="delete" size={24} />
                    <Text>Delete account</Text>
                </TouchableOpacity>
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
        margin: 10,
        rowGap: 20
    },
    subcontainer: {
        rowGap: 10,
    },
    optionTitle: {
        fontSize: 18,
        fontWeight: "bold"
    },
    optionInput: {
        backgroundColor: "white",
        borderColor: "#000",
        borderRadius: 10,
        borderWidth: 1,
        paddingHorizontal: 10
    },
    avatar: {
        backgroundColor: "gray",
        borderRadius: 100,
        alignSelf: "center"
    },
    button: {
        borderRadius: 20,
        borderWidth: 2,
        flexDirection: "row",
        alignItems: "center",
        padding: 10,
        justifyContent: "center",
        columnGap: 5
    },
    dangerZone: {
        borderWidth: 1,
        borderColor: "red",
        padding: 20,
        margin: 10,
        borderRadius: 10,
    }
})