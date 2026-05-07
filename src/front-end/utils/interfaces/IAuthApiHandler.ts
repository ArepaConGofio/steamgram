import { LoginRequest, LoginResponse, RegisterRequest, RegisterResponse } from "@/models/Auth"
import { UserId } from "@/models/User"

export interface IAuthApiHandler {
    login(credentials: LoginRequest): Promise<LoginResponse>
    register(credentials: RegisterRequest): Promise<RegisterResponse>
    deleteAccount(id: UserId): Promise<void>
    checkUsernameAvailability(username: string): Promise<boolean> 
}