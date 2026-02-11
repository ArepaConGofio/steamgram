import { User } from "@/models/User";

export interface IUsersAPIHandler {
    /**
     * Get all users. The query can be limited.
     * @param limit - Max users.
     */
    getAllUsers(limit?: number): Promise<User[]>
}