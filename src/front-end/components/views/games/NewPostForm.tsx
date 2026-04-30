import { AuthContext } from "@/context/AuthContext";
import { Game, GameId } from "@/models/Game";
import { GamesAPIHandler } from "@/utils/GamesAPIHandler";
import { PostsAPIHandler } from "@/utils/PostsAPIHandler";
import { MaterialIcons } from "@expo/vector-icons";
import { useContext, useEffect, useState } from "react";
import { Alert, Image, Modal, Pressable, StyleSheet, Text, TextInput, View } from "react-native";

type Props = {
  gameId: GameId;
}

export default function NewPostForm({ gameId }: Props) {
  const { user } = useContext(AuthContext)
  const [modalVisible, setModalVisible] = useState(false);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [imageUrl, setImageUrl] = useState("");
  const [currentGame, setCurrentGame] = useState<Game>();
  const [imagePromptVisible, setImagePromptVisible] = useState<boolean>(false);

  const addImage = () => {
    setDescription(description + `![image](${imageUrl})`);
    setImageUrl("");
  }

  const sendPost = () => {
    if (user == null) return;
    var json = JSON.stringify(description);
    const api = new PostsAPIHandler();
    api.createPost({ gameId: gameId, title: title, userId: user.id, description: json })
    .catch(reason => console.error("ERROR: Something bad happen trying create the post", reason));
  }

  const askForSend = () => {
    if (user == null) {
      console.error("ERROR: The user is null while trying send the post");
      return;
    }
        if (title.length == 0 || description.length == 0) {
      Alert.alert("Warning", "The content is required")
      return;
    }
    Alert.alert("Posting", `You are posting about ${currentGame?.name}. Do you want to continue?`, [
      { text: "Yeah", onPress: () => sendPost() },
      { text: "Nope" }
    ])
  }

  useEffect(() => {
    const gameApi = new GamesAPIHandler();
    gameApi.getGameDetails(gameId)
      .then(value => setCurrentGame(value))
      .catch(reason => Alert.alert("Error", reason))
  }, [gameId])

  return (
    <View>
      <Modal
        animationType="fade"
        backdropColor={"#000000ac"}
        visible={modalVisible}
        onRequestClose={() => setModalVisible(!modalVisible)}>
        <View style={styles.centeredView}>
          <View style={styles.modalView}>

            <View style={styles.modalHeader}>
              <View style={styles.itemContainer}>
                <Image src={currentGame?.coverUrl} height={50} width={50} style={styles.gameCover} />
                <Text style={styles.itemLabel}>{currentGame?.name}</Text>
              </View>
              <Pressable onPress={() => setModalVisible(!modalVisible)}>
                <MaterialIcons name="close" size={20} />
              </Pressable>
            </View>

            <View style={styles.modalBody}>
              <TextInput placeholder="Express thyself!" maxLength={30}
                style={styles.postTitle} onChangeText={setTitle} value={title} />

              <TextInput editable multiline style={styles.postDescription}
                placeholder="What do you think about this masterpiece? :D"
                maxLength={2000} value={description} onChangeText={setDescription} />
            </View>

            <View style={[styles.imageInput, { display: imagePromptVisible ? "flex" : "none" }]}>
              <TextInput placeholder="Inserte el enlace de la imagen..."
                style={{ flex: 0.8 }}
                onChangeText={setImageUrl} value={imageUrl} />
              <Pressable style={{ flex: 0.1 }} onPress={addImage}>
                <MaterialIcons name="add" size={30} />
              </Pressable>
            </View>

            <View style={styles.modalActions}>
              <View style={styles.buttonGroup}>
                <Pressable onPress={() => setImagePromptVisible(!imagePromptVisible)}>
                  <MaterialIcons name="image" size={20} />
                </Pressable>
              </View>
              <View style={styles.buttonGroup}>
                <Text style={{ color: "gray" }}>{description.length}/2000</Text>
                <Pressable onPress={askForSend}>
                  <MaterialIcons name="send" size={20} />
                </Pressable>
              </View>
            </View>
          </View>
        </View>
      </Modal>
      <Pressable onPress={() => setModalVisible(true)} style={styles.button}>
        <MaterialIcons name="add" size={20} />
        <Text>Publish a new post!</Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  centeredView: {
    flex: 1,
    justifyContent: 'center',
  },
  modalView: {
    flex: 0.85,
    margin: 20,
    backgroundColor: 'white',
    borderRadius: 20,
    padding: 20,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 4,
    elevation: 5,
    rowGap: 10
  },
  modalHeader: {
    rowGap: 10,
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
    borderBottomColor: "gray",
    borderBottomWidth: 1,
    paddingBottom: 10
  },
  modalBody: {
    flex: 1,
  },
  modalActions: {
    flexDirection: "row",
    justifyContent: "space-between",
    columnGap: 15
  },
  button: {
    borderRadius: 20,
    padding: 10,
    borderColor: "#000",
    borderWidth: 1,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#fff",
    elevation: 2,
    flexDirection: "row",
    columnGap: 10
  },
  buttonGroup: {
    flexDirection: "row",
    columnGap: 10
  },
  buttonOpen: {
    backgroundColor: '#F194FF',
  },
  buttonClose: {
    backgroundColor: '#2196F3',
  },
  textStyle: {
    color: 'white',
    fontWeight: 'bold',
    textAlign: 'center',
  },
  modalText: {
    marginBottom: 15,
    textAlign: 'center',
  },
  itemContainer: {
    flexDirection: "row",
    columnGap: 10,
    alignItems: "center"
  },
  itemLabel: {
    fontSize: 18,
    textAlignVertical: "center"
  },
  postTitle: {
    fontSize: 20
  },
  postDescription: {
  },
  gameCover: {
    borderRadius: 10
  },
  imageInput: {
    borderTopColor: "gray",
    borderTopWidth: 1,
    borderBottomWidth: 1,
    borderBottomColor: "gray",
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center"
  }
});