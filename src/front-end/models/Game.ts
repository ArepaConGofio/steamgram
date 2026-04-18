export type GameId = number;

export type Game = {
  id: GameId;
  steamId?: string;
  igdbId?: string;
  developerId?: number
  developerName?: string;
  name: string;
  coverUrl?: string;
  artworkUrl?: string;
  description?: string;
  likesCount: number;
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