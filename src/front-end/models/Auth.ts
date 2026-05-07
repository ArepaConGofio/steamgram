import { User } from "./User";

export type LoginResponse = {
    isValid: boolean;
    token?: string;
    user?: User;
};

export type RegisterResponse = {
    isValid: boolean;
    token?: string;
    user?: User
}

export type LoginRequest = {
    username: string;
    password: string;
}
 
export type RegisterRequest = LoginRequest & {
    email: string;
    nickname: string;
}