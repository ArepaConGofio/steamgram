import { AuthResponse, LoginRequest, RegisterRequest } from "@/models/Auth";
import { UserId } from "@/models/User";
import { APIHandler, HttpMethods } from "./APIHandler";
import { IAuthApiHandler } from "./interfaces/IAuthApiHandler";

export class AuthAPIHandler extends APIHandler implements IAuthApiHandler {
    async login(credentials: LoginRequest): Promise<AuthResponse> {
        const response = await APIHandler.makeRequest({ 
            endpoint: "/auth/login",
            method: HttpMethods.POST,
            body: credentials
        })
        if (response == null) {
            return { isValid: false, message: "The user not exists!" }
        }
        return { isValid: true, token: response.token, user: {
            id: response.user.id,
            email: response.user.email,
            username: response.user.nickname,
            nickname: response.user.name,
            avatarUrl: response.user.avatarUrl
        }}
    }

    async register(credentials: RegisterRequest): Promise<AuthResponse> {
        const response = await APIHandler.makeRequest({
            endpoint: "/auth/register",
            method: HttpMethods.POST,
            body: { 
                "nickname": credentials.username, 
                "email": credentials.email,
                "name": credentials.nickname,
                "password": credentials.password
            }
        })
        if (response == null) {
            return { isValid: false, message: "Username or email not available" }
        }
        return { isValid: true }
    }

    async deleteAccount(id: UserId): Promise<void> {
        await APIHandler.makeRequest({
            endpoint: `/user/${id}`,
            method: HttpMethods.DELETE,
            token: true
        })
    }

    async checkUsernameAvailability(username: string): Promise<boolean> {
        const response = await APIHandler.makeRequest({ endpoint: `/user/check/${username}` });
        return !response ? false : true;
    }
}