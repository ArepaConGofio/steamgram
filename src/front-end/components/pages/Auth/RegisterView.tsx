import { authFormStyles } from "@/styles/AuthFormStyles";
import { useRef } from "react";
import { StyleSheet, Text, TextInput, TouchableOpacity, View } from "react-native";

type RegisterGetters = {
  username: string;
  email: string;
  password: string;
  repeatedPassword: string;
  nickname: string;
};

type RegisterSetters = {
  setUsername: React.Dispatch<React.SetStateAction<string>>;
  setEmail: React.Dispatch<React.SetStateAction<string>>;
  setPassword: React.Dispatch<React.SetStateAction<string>>;
  setRepeatedPassword: React.Dispatch<React.SetStateAction<string>>;
  setNickname: React.Dispatch<React.SetStateAction<string>>;
};

type RegisterCallbacks = {
  goToLogin: () => void;
  submit: () => void;
};

type Props = {
  getters: RegisterGetters;
  setters: RegisterSetters;
  callbacks: RegisterCallbacks;
};

export default function RegisterView({ getters, setters, callbacks }: Props) {
  const input2ref = useRef<TextInput>(null);
  const input3ref = useRef<TextInput>(null);
  const input4ref = useRef<TextInput>(null);
  const input5ref = useRef<TextInput>(null);

  return (
    <View style={authFormStyles.formContainer}>
      <Text style={authFormStyles.title}>Register</Text>
      <View style={authFormStyles.form}>

        <Text style={styles.inputLabel}>Username*</Text>
        <TextInput
          style={styles.input}
          value={getters.username}
          onChangeText={setters.setUsername}
          placeholder="Insert your username..."
          returnKeyType="next"
          onSubmitEditing={() => { input2ref.current?.focus() }}
          submitBehavior="submit" />

        <Text style={styles.inputLabel}>Nickname</Text>
        <TextInput
          ref={input2ref}
          style={styles.input}
          value={getters.nickname}
          onChangeText={setters.setNickname}
          placeholder="Insert your nickname..."
          returnKeyType="next"
          onSubmitEditing={() => { input3ref.current?.focus() }} 
          submitBehavior="submit"/>

        <Text style={styles.inputLabel}>Email*</Text>
        <TextInput
          style={styles.input}
          ref={input3ref}
          value={getters.email}
          onChangeText={setters.setEmail}
          placeholder="Insert your email..."
          returnKeyType="next"
          onSubmitEditing={() => { input4ref.current?.focus() }} />

        <Text style={styles.inputLabel}>Password*</Text>
        <TextInput
          style={styles.input}
          ref={input4ref}
          value={getters.password}
          onChangeText={setters.setPassword}
          placeholder="The password must be minimum 8 characters..."
          returnKeyType="next"
          onSubmitEditing={() => { input5ref.current?.focus() }} 
          submitBehavior="submit"/>

        <Text style={styles.inputLabel}>Repeat password*</Text>
        <TextInput
          ref={input5ref}
          style={styles.input}
          value={getters.repeatedPassword}
          onChangeText={setters.setRepeatedPassword}
          placeholder="Repeat the password!"
          returnKeyType="done"
          onSubmitEditing={callbacks.submit} />

        <TouchableOpacity
          style={authFormStyles.formSubmit}
          onPress={callbacks.submit}
        >
          <Text style={authFormStyles.formSubmitLabel}>Register</Text>
        </TouchableOpacity>
      </View>
      <Text onPress={callbacks.goToLogin} style={authFormStyles.toAltPage}>
        Already have an account? Log in!
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