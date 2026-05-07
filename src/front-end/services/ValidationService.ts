import { AuthAPIHandler } from "@/utils/AuthAPIHandler";

export type ValidationResult = {
    isValid: boolean;
    message?: string;
}

export class ValidationService  {
    static async isUsernameAvailable(username: string): Promise<ValidationResult> {
        if (username.length == 0) {
            return { isValid: false, message: "The username is required!" };
        }

        const authAPI = new AuthAPIHandler();
        const isAvailable = await authAPI.checkUsernameAvailability(username);
        if (!isAvailable) {
            return { isValid: false, message: "The username is not available." };
        }
        return { isValid: true };
    }

    static validateEmail(email: string): ValidationResult {
        if (email.length == 0) {
            return { isValid: false, message: "The email is required!" }
        }

        const EMAIL_REGEX = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!email.match(EMAIL_REGEX)) {
            return { isValid: false, message: "The email is not valid." }; 
        }
        return { isValid: true }
    }

    static validatePassword(password: string): ValidationResult {
        const MIN_PASSWORD_LENGTH = 8;
        const SPECIAL_CHARACTERS = "!@#$&?¿¡€";
    
        if (password.length < MIN_PASSWORD_LENGTH) {
            return { isValid: false, message: "The password must be, minimum, 8 characters." }
        }
        if (!password.match("[A-Z]")) {
            return { isValid: false, message: "The password must be contain one uppercase letter." }
        }
        if (!password.match("[1-9]")) {
            return { isValid: false, message: "The password must be contain one numeric character." }
        }
        if (!password.match(`[${SPECIAL_CHARACTERS}]`)) {
            return { isValid: false, message: "The password must be contain one of this characters: " + SPECIAL_CHARACTERS }
        }
        return { isValid: true }
    }
}
