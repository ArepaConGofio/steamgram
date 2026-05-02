import { User } from "@/models/User";
import React, { createContext, useCallback, useMemo, useState } from "react";

type AuthContextType = {
  token: string | null;
  user: User | null;
  login: (username: string, password: string) => Promise<unknown>;
  register: (username: string, password: string) => Promise<unknown>;
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

  const login = useCallback(async (username: string, password: string) => {
    // TODO: Implements real login
    if (true) {
      setToken("token");
      setUser({
        id: 6,
        username: "user",
        nickname: "",
        email: "test@example.com",
      });
      return true;
    }
    // return false;
  }, []);

  const register = useCallback(async (username: string, password: string) => {
    throw new Error("Not implemented");
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
