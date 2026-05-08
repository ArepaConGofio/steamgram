export type GameId = number;

export type Game = {
  id: GameId;
  idIgdb: number;
  developerId: number
  developerName: string;
  title: string;
  description?: string;
  banner?: string;
  genres: string[];
  platforms: string[]
  screenshots: string[];
  reviewsCount: number,
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