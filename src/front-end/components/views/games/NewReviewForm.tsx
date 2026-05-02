import { AuthContext } from "@/context/AuthContext";
import { Game, GameId } from "@/models/Game";
import { ReviewCreationRequest } from "@/models/Review";
import { GamesAPIHandler } from "@/utils/GamesAPIHandler";
import { MaterialIcons } from "@expo/vector-icons";
import { useContext, useEffect, useState } from "react";
import { Alert, Image, Modal, StyleSheet, Text, TextInput, TouchableOpacity, View } from "react-native";

type Props = {
  gameId: GameId
}

export default function NewReviewForm({ gameId }: Props) {
  const { user } = useContext(AuthContext); 
  const [modalVisible, setModalVisible] = useState(false);
  const [currentGame, setCurrentGame] = useState<Game>();
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [rating, setRating] = useState(3);

  const askForSend = () => {
    if (title.length == 0 || description.length == 0) {
      Alert.alert("Warning", "The content is required")
      return;
    }
    Alert.alert("Uploading review", `You are uploading a review for ${currentGame?.name} with a score of ${rating} stars. Do you want to continue?`, [
      { text: "Sure!", onPress: () => sendReview() },
      { text: "Nah" }
    ])
  }

  const sendReview = () => {
    if (user == null || currentGame == null) return;
    const review: ReviewCreationRequest = {
      author: user?.username,
      gameId: gameId,
      gameTitle: currentGame?.name,
      rating: rating,
      title: title,
      userId: user.id,
      description: description
    }
    console.log(review);
    // TODO: Backend request to upload review.
  }

  const generateStarButtons = () => {
    return (
      [1, 2, 3, 4, 5].map((value) => (
        <TouchableOpacity key={value} onPress={() => setRating(value)}>
          <MaterialIcons name={rating >= value ? "star" : "star-outline"} size={24}/>
        </TouchableOpacity>
      ))
    )
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
              <TouchableOpacity onPress={() => setModalVisible(!modalVisible)}>
                <MaterialIcons name="close" size={20} />
              </TouchableOpacity>
            </View>

            <View style={styles.modalBody}>
              <TextInput placeholder="Express thyself!" maxLength={30}
                style={styles.postTitle} onChangeText={setTitle} value={title} />

              <TextInput editable multiline style={styles.postDescription}
                placeholder="Do you like it? Do you hate it? Tell me..."
                maxLength={200} value={description} onChangeText={setDescription} />
            </View>

            <View style={styles.modalActions}>
              <View style={styles.buttonGroup}>
                {generateStarButtons()}
              </View>
              <View style={styles.buttonGroup}>
                <Text style={{ color: "gray" }}>{description.length}/200</Text>
                <TouchableOpacity onPress={askForSend}>
                  <MaterialIcons name="send" size={20} />
                </TouchableOpacity>
              </View>
            </View>
          </View>
        </View>
      </Modal>
      <TouchableOpacity onPress={() => setModalVisible(true)} style={styles.button}>
        <MaterialIcons name="add" size={20} />
        <Text>Write a review!</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  centeredView: {
    flex: 1,
    justifyContent: 'center',
  },
  modalView: {
    flex: 0.5,
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