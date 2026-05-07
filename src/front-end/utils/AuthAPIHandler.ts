import { AuthContext } from "@/context/AuthContext";
import { LoginRequest, LoginResponse, RegisterRequest, RegisterResponse } from "@/models/Auth";
import { UserId } from "@/models/User";
import { useContext } from "react";
import { APIHandler, HttpMethods } from "./APIHandler";
import { IAuthApiHandler } from "./interfaces/IAuthApiHandler";

export class AuthAPIHandler extends APIHandler implements IAuthApiHandler {
    async login(credentials: LoginRequest): Promise<LoginResponse> {
        const response = await APIHandler.makeRequest({ 
            endpoint: "/auth/login",
            method: HttpMethods.POST,
            body: credentials
        })
        if (response == null) {
            return { isValid: false }
        }
        return { isValid: true, token: response.token, user: response.user }
    }

    async register(credentials: RegisterRequest): Promise<RegisterResponse> {
        const response = await APIHandler.makeRequest({
            endpoint: "/auth/register",
            method: HttpMethods.POST,
            body: credentials
        })
        if (response != null) {
            return { isValid: false }
        }
        return { isValid: true, user: response }
    }

    async deleteAccount(id: UserId): Promise<void> {
        const { token } = useContext(AuthContext);
        await APIHandler.makeRequest({
            endpoint: `/user/${id}`,
            method: HttpMethods.DELETE,
            token: token
        })
    }

    async checkUsernameAvailability(username: string): Promise<boolean> {
        const response = await APIHandler.makeRequest({ endpoint: `/user/check/${username}` });
        return !response ? false : true;
    }
}