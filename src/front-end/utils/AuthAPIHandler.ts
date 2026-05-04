import { LoginRequest, LoginResponse, RegisterRequest, RegisterResponse } from "@/models/Auth";
import { APIHandler } from "./APIHandler";
import { IAuthApiHandler } from "./interfaces/IAuthApiHandler";

export class AuthAPIHandler extends APIHandler implements IAuthApiHandler {
    async login(credentials: LoginRequest): Promise<LoginResponse> {
        // TODO: Implements login credentials on request body with password included.
        const users = await APIHandler.makeRequest({ endpoint: `/users?username=${credentials.username}` })
        if (!users|| users[0].username !== credentials.username) {
            return { isValid: false }
        }
        // TODO: Implements real token assignment
        return { isValid: true, token: "token-1234", user: {
            id: users[0].id,
            username: users[0].username,
            email: users[0].email,
            avatarUrl: users[0].avatarUrl,
            nickname: users[0].nickname
        }}
    }

    async register(credentials: RegisterRequest): Promise<RegisterResponse> {
        // TODO: Implements real register
        return { isValid: true }
    }

    async deleteAccount(): Promise<boolean> {
        // TODO: Implements real account deleting
        return true;
    }

    async checkUsernameAvailability(username: string): Promise<boolean> {
        const users = await APIHandler.makeRequest({ endpoint: `/users?username=${username}` }) as Array<unknown>;
        return users.length == 0;
    }
}