import { Constants } from "@/Constants";

export enum HttpMethods {
  GET = "get",
  POST = "post",
  PUT = "put",
  DELETE = "delete",
}

export type RequestData = {
  endpoint: string;
  method?: HttpMethods;
  body?: BodyInit | null | any;
  token?: string|null;
};

export class APIHandler {
  private static generateHeaders(token?: string|null): Headers {
    const headers = new Headers();
    headers.set("Content-Type", "application/json");
    if (token) {
      headers.set("Authorization", token);
    }
    return headers;
  }

  static async makeRequest(requestData: RequestData) {
    const requestMethod = requestData.method ? requestData.method : HttpMethods.GET;
    const requestUrl = Constants.API_URL + requestData.endpoint
    try {
      const response = await fetch(requestUrl, {
        method: requestMethod.toString(),
        headers: this.generateHeaders(requestData.token),
        body: JSON.stringify(requestData.body),
      });
      return await response.json();
    } catch (error) {
      const errorMsg = `ERROR: Something wrong happend while trying to fetch data in ${requestUrl}`;
      console.error(errorMsg, error);
    }
  }
}
