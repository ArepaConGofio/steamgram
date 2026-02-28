import ProfileContent from "@/components/pages/users/ProfileContent";
import { ProfileContentType, UserDetails } from "@/models/User";
import { useState } from "react";

type Props = {
    user: UserDetails
}

export default function ProfileContainer({ user }: Props) {
    const [currentTab, setCurrentTab] = useState<ProfileContentType>("Games");

    return <ProfileContent user={user} selectContentTab={setCurrentTab} contentTab={currentTab}/>
}