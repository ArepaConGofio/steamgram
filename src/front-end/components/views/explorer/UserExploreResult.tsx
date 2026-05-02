import { User } from "@/models/User"
import { Text } from "react-native"

type Props = {
    user: User
}

export default function UserExploreResult({ user }: Props) {
    return (
        <Text>Username: {user.username} - Nickname: {user.nickname}</Text>
    )
}