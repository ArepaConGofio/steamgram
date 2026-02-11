import { StyleSheet } from "react-native";

export const authFormStyles = StyleSheet.create({
  formContainer: {
    marginHorizontal: 30,
  },
  form: {
    borderWidth: 1,
    borderColor: "#D9C4BF",
    borderRadius: 20,
    padding: 25,
    marginVertical: 10,
  },
  formSubmit: {
    marginTop: 10,
    backgroundColor: "#013440",
    borderRadius: 10,
    padding: 10,
  },
  formSubmitLabel: {
    color: "#ffffff",
    textAlign: "center",
    fontWeight: "bold",
  },
  toAltPage: {
    color: "#5C82F2",
    textDecorationLine: "underline",
  },
});