import * as ExpoConstants from 'expo-constants';

export class Constants {
    static API_URL = "http://" + ExpoConstants.default.expoConfig?.hostUri?.split(":")[0] + ":8080/api"
}