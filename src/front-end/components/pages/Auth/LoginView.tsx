import { authFormStyles } from "@/styles/AuthFormStyles";
import { useRef } from "react";
import { StyleSheet, Text, TextInput, TouchableOpacity, View } from "react-native";

type LoginGetters = {
  username: string;
  password: string;
};

type LoginSetters = {
  setUsername: React.Dispatch<React.SetStateAction<string>>;
  setPassword: React.Dispatch<React.SetStateAction<string>>;
};

type LoginCallbacks = {
  goToRegister: () => void;
  submit: () => void;
};

type Props = {
  getters: LoginGetters;
  setters: LoginSetters;
  callbacks: LoginCallbacks;
};

export default function LoginView({ getters, setters, callbacks }: Props) {
  const input2Ref = useRef<TextInput>(null);

  return (
    <View style={authFormStyles.formContainer}>
      <Text style={authFormStyles.title}>Login</Text>
      <View style={authFormStyles.form}>

        <Text style={styles.inputLabel}>Username</Text>
        <TextInput
          style={styles.input}
          value={getters.username}
          onChangeText={setters.setUsername}
          placeholder="Insert your username..."
          returnKeyType="next"
          onSubmitEditing={() => { input2Ref.current?.focus() }} 
          submitBehavior="submit"/>

        <Text style={styles.inputLabel}>Password</Text>
        <TextInput
          ref={input2Ref}
          style={styles.input}
          value={getters.password}
          onChangeText={setters.setPassword}
          placeholder="Insert your password..."
          returnKeyType="done"
          onSubmitEditing={callbacks.submit} 
          secureTextEntry/>

        <TouchableOpacity
          style={authFormStyles.formSubmit}
          onPress={callbacks.submit}
        >
          <Text style={authFormStyles.formSubmitLabel}>Login</Text>
        </TouchableOpacity>
      </View>
      <Text onPress={callbacks.goToRegister} style={authFormStyles.toAltPage}>
        No account? Sign up!
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  input: {
    borderColor: "#D9C4BF",
    borderWidth: 1,
    borderRadius: 10,
    marginVertical: 5,
    paddingHorizontal: 10,
  },
  inputLabel: {
    fontWeight: "bold",
  },
  inputContainer: {
    marginVertical: 8,
  }
});