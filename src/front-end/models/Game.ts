export type GameId = number;

export type Game = {
  id: GameId;
  steamId?: string;
  developerId?: number
  publisher?: string;
  name: string;
  coverUrl: string;
  description: string;
};

export type GameCreationRequest = {
  developerId: number;
  name: string;
  publisher?: string;
  steamId?: string;
  coverUrl: string;
  description: string;
}

export type GameCreationResponse = {
  id: GameId;
  name: string;
  developerId: number;
}