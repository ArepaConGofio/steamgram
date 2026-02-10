import React from "react";
import { Pressable, StyleSheet } from "react-native";

type Props = {
  onPress: () => void;
  orientation?: "row" | "row-reverse" | "column" | "column-reverse";
  children: React.ReactNode;
};

export default function Button({ onPress, orientation, children }: Props) {
  return (
    <Pressable
      style={[style.button, { flexDirection: orientation }]}
      onPress={onPress}
    >
      {children}
    </Pressable>
  );
}

const style = StyleSheet.create({
  button: {
    justifyContent: "center",
    alignItems: "center",
  },
});
