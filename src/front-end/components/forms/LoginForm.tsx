import LabeledTextInput from "@/components/ui/TextInput";
import { authFormStyles } from "@/styles/AuthFormStyles";
import { globalStyles } from "@/styles/GlobalStyles";
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
    goToRegister: () => void
}

type Props = {
  getters: LoginGetters;
  setters: LoginSetters;
  callbacks: LoginCallbacks;
};

export default function LoginForm({ getters, setters, callbacks }: Props) {
  return (
      <View style={authFormStyles.formContainer}>
        <Text style={globalStyles.title}>Login</Text>
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
          />
          <TouchableOpacity style={authFormStyles.formSubmit}>
            <Text style={authFormStyles.formSubmitLabel}>Register</Text>
          </TouchableOpacity>
        </View>
        <Text
          onPress={callbacks.goToRegister}
          style={authFormStyles.toAltPage}
        >
          No account? Sign up!
        </Text>
      </View>
  );
}
