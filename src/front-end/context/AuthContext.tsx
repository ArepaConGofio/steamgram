import React, { createContext, useCallback, useMemo, useState } from "react";

type AuthContextType = {
  token: string | null;
  login: (username: string, password: string) => Promise<unknown>;
  register: (username: string, password: string) => Promise<unknown>;
  logout: () => Promise<void>;
};

export const AuthContext = createContext<AuthContextType>({
  token: null,
  login: async () => ({}),
  register: async () => ({}),
  logout: async () => {},
});

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const [token, setToken] = useState(null);

  const login = useCallback(async (username: string, password: string) => {
    throw new Error("Not implemented");
  }, []);

  const register = useCallback(async (username: string, password: string) => {
    throw new Error("Not implemented");
  }, []);

  const logout = useCallback(async () => {
    throw new Error("Not implemented");
  }, []);

  const contextValue = useMemo(
    () => ({
      token,
      setToken,
      login,
      register,
      logout,
    }),
    [login, logout, register, token],
  );
  return (
    <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>
  );
}
