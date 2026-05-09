import { User } from "./User";

export type AuthResponse = {
    isValid: boolean;
    message?: string;
    token?: string;
    user?: User;
};

export type LoginRequest = {
    username: string;
    password: string;
}
 
export type RegisterRequest = LoginRequest & {
    email: string;
    nickname: string;
}