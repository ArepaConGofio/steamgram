import LabeledTextInput from "@/components/ui/TextInput";
import { authFormStyles } from "@/styles/AuthFormStyles";
import { Text, TouchableOpacity, View } from "react-native";

type RegisterGetters = {
  username: string;
  email: string;
  password: string;
  repeatedPassword: string;
};

type RegisterSetters = {
  setUsername: React.Dispatch<React.SetStateAction<string>>;
  setEmail: React.Dispatch<React.SetStateAction<string>>;
  setPassword: React.Dispatch<React.SetStateAction<string>>;
  setRepeatedPassword: React.Dispatch<React.SetStateAction<string>>;
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
  return (
    <View style={authFormStyles.formContainer}>
      <Text style={authFormStyles.title}>Register</Text>
      <View style={authFormStyles.form}>
        <LabeledTextInput
          label="Username"
          setter={setters.setUsername}
          placeholder="Insert your username here!"
          value={getters.username}
        />
        <LabeledTextInput
          label="Nickname"
          setter={setters.setUsername}
          placeholder="This is what other users will see (optional)"
          value={getters.username}
        />
        <LabeledTextInput
          label="Email"
          setter={setters.setEmail}
          placeholder="Insert your email here!"
          value={getters.email}
        />
        <LabeledTextInput
          label="Password"
          setter={setters.setPassword}
          value={getters.password}
          placeholder="The password must be minimun 8 characters..."
          isSecureEntry
        />
        <LabeledTextInput
          label="Repeat password"
          setter={setters.setRepeatedPassword}
          value={getters.repeatedPassword}
          placeholder="Repeat the password!"
          isSecureEntry
        />
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
