import { Constants } from "@/Constants";

export enum HttpMethods {
  GET,
  POST,
  PUT,
  DELETE,
}

export type RequestData = {
  endpoint: string;
  method?: HttpMethods;
  body?: BodyInit | null;
  token?: string;
};

export class APIHandler {
  private static generateHeaders(token?: string): Headers {
    const headers = new Headers();
    headers.set("Content-Type", "application/json");
    if (token) {
      headers.set("Authorization", token);
    }
    return headers;
  }

  static async makeRequest(requestData: RequestData) {
    const requestMethod = requestData.method ? requestData.method : HttpMethods.GET;
    try {
      const response = await fetch(Constants.API_URL + requestData.endpoint, {
        method: requestMethod.toString(),
        headers: this.generateHeaders(requestData.token),
        body: JSON.stringify(requestData.body),
      });
      return await response.json();
    } catch (error) {
      console.error(
        "ERROR: Something wrong happend while trying to fetch data",
        error
      );
    }
  }
}
