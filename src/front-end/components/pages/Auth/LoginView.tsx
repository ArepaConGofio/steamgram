import LabeledTextInput from "@/components/ui/TextInput";
import { authFormStyles } from "@/styles/AuthFormStyles";
import { Text, TouchableOpacity, View } from "react-native";

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
  return (
    <View style={authFormStyles.formContainer}>
      <Text style={authFormStyles.title}>Login</Text>
      <View style={authFormStyles.form}>
        <LabeledTextInput
          label="Username"
          setter={setters.setUsername}
          value={getters.username}
        />
        <LabeledTextInput
          label="Password"
          setter={setters.setPassword}
          value={getters.password}
          isSecureEntry
        />
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
