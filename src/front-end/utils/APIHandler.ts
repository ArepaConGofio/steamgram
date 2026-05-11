import { Constants } from "@/Constants";
import * as SecureStore from 'expo-secure-store';

export enum HttpMethods {
  GET = "get",
  POST = "post",
  PUT = "put",
  DELETE = "delete",
  PATCH = "patch"
}

export type RequestData = {
  endpoint: string;
  method?: HttpMethods;
  body?: BodyInit | null | any;
  token?: boolean;
};

export class APIHandler {
  private static async generateHeaders(withToken: boolean): Promise<Headers> {
    const headers = new Headers();
    headers.set("Content-Type", "application/json");
    if (withToken) {
      const token = await SecureStore.getItemAsync("token");
      headers.set("Authorization", token ? `Bearer ${token}` : "");
    }
    return headers;
  }

  static async makeRequest(requestData: RequestData) {
    const requestMethod = requestData.method ? requestData.method : HttpMethods.GET;
    const requestUrl = Constants.API_URL + requestData.endpoint
    const options = {
        method: requestMethod.toString(),
        headers: await this.generateHeaders(requestData.token ? true : false),
        body: JSON.stringify(requestData.body),
    }
    try {
      const response = await fetch(requestUrl, options);
      console.log(requestMethod.toUpperCase(), response.status, response.url)
      if (response.ok) {
        try {
          return await response.json();
        } catch (error) {
          console.warn("Response is not json parseable");
          return;
        }
      }
      console.warn(response)
    } catch (error) {
      const errorMsg = `ERROR: Something wrong happend while trying to fetch data in ${requestUrl}`;
      console.error(errorMsg, error);
    }
  }
}
