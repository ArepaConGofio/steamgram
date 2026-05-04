import { LoginRequest, RegisterRequest } from "@/models/Auth";
import { User } from "@/models/User";
import { ValidationService } from "@/services/ValidationService";
import { AuthAPIHandler } from "@/utils/AuthAPIHandler";
import React, { createContext, useCallback, useMemo, useState } from "react";

type AuthContextType = {
  token: string | null;
  user: User | null;
  login: (credentials: LoginRequest) => Promise<unknown>;
  register: (credentials: RegisterRequest) => Promise<unknown>;
  logout: () => Promise<void>;
};

export const AuthContext = createContext<AuthContextType>({
  token: null,
  user: null,
  login: async () => ({}),
  register: async () => ({}),
  logout: async () => {},
});

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const [token, setToken] = useState<string | null>(null);
  const [user, setUser] = useState<User | null>(null);

  const login = useCallback(async (credentials: LoginRequest) => {
    const authAPI = new AuthAPIHandler();
    const response = await authAPI.login(credentials);
    if (!response.isValid || !response.token || !response.user ) {
      return { isValid: false, message: "Something wrong happend trying login... Try again later." };
    }

    setToken(response.token);
    setUser(response.user);
    return { isValid: true, token: response.token };
  }, []);

  const register = useCallback(async (credentials: RegisterRequest) => {
    const usernameValidation = await ValidationService.isUsernameAvailable(credentials.username);
    if (!usernameValidation.isValid) return usernameValidation;
    const emailValidation = ValidationService.validateEmail(credentials.email);
    if (!emailValidation.isValid) return emailValidation;
    const passwordValidation = ValidationService.validatePassword(credentials.password, credentials.repeatedPassword);
    if (!passwordValidation.isValid) return passwordValidation;

    const authAPI = new AuthAPIHandler();
    const registerResponse = await authAPI.register(credentials);
    if (!registerResponse.isValid) {
      return { isValid: false, message: "Something wrong happend trying to register. Please, try again later..." }
    }
    return await login({ username: credentials.username, password: credentials.password });
  }, []);

  const logout = useCallback(async () => {
    setToken(null);
    setUser(null);
  }, []);

  const contextValue = useMemo(
    () => ({
      token,
      user,
      setToken,
      login,
      register,
      logout,
    }),
    [login, logout, register, token, user]
  );
  return (
    <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>
  );
}
