import { LoginRequest, LoginResponse, RegisterRequest, RegisterResponse } from "@/models/Auth"

export interface IAuthApiHandler {
    login(credentials: LoginRequest): Promise<LoginResponse>
    register(credentials: RegisterRequest): Promise<RegisterResponse>
    deleteAccount(): Promise<boolean>
    checkUsernameAvailability(username: string): Promise<boolean> 
}