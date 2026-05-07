import { LoginRequest, RegisterRequest } from "@/models/Auth";
import { User } from "@/models/User";
import { ValidationService } from "@/services/ValidationService";
import { AuthAPIHandler } from "@/utils/AuthAPIHandler";
import { router } from "expo-router";
import * as SecureStore from 'expo-secure-store';
import React, { createContext, useCallback, useMemo, useState } from "react";

type AuthContextType = {
  user: User | null; 
  editUser: (user: User) => void;
  login: (credentials: LoginRequest) => Promise<unknown>;
  register: (credentials: RegisterRequest) => Promise<unknown>;
  logout: () => Promise<void>;
};

export const AuthContext = createContext<AuthContextType>({
  user: null,
  editUser: () => ({}),
  login: async () => ({}),
  register: async () => ({}),
  logout: async () => {},
});

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const [user, setUser] = useState<User | null>(null);

  const login = useCallback(async (credentials: LoginRequest) => {
    if (credentials.username.length == 0 || credentials.password.length == 0) {
      return { isValid: false, message: "The credentials are required!" }
    }

    const authAPI = new AuthAPIHandler();
    const response = await authAPI.login(credentials);
    if (!response.isValid || !response.token || !response.user ) {
      return { isValid: false, message: "Something wrong happend trying login: " + response.message };
    }
    setUser(response.user);
    await SecureStore.setItemAsync("token", response.token)
    return { isValid: true, token: response.token };
  }, []);

  const register = useCallback(async (credentials: RegisterRequest) => {
    const usernameValidation = await ValidationService.validateUsername(credentials.username);
    if (!usernameValidation.isValid) return usernameValidation;
    const emailValidation = ValidationService.validateEmail(credentials.email);
    if (!emailValidation.isValid) return emailValidation;
    const passwordValidation = ValidationService.validatePassword(credentials.password);
    if (!passwordValidation.isValid) return passwordValidation;

    const authAPI = new AuthAPIHandler();
    const response = await authAPI.register(credentials);
    if (!response.isValid) {
      return { isValid: false, message: "Something wrong happend trying to register: " + response.message }
    }
    return await login({ username: credentials.username, password: credentials.password });
  }, []);

  const editUser = useCallback((user: User) => {
    setUser(user);
  }, [])

  const logout = useCallback(async () => {
    await SecureStore.deleteItemAsync("token")
    setUser(null);
    router.replace("/login")
  }, []);

  const contextValue = useMemo(
    () => ({
      user,
      editUser,
      login,
      register,
      logout,
    }),
    [login, logout, register, user]
  );
  return (
    <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>
  );
}
