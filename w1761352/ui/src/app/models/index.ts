//types for angular premier league app
export interface IScore {
    clubName: string;
    totalMatches: number;
    wins: number;
    draws: number;
    defeats: number;
    points: number;
    goalsScored: number;
    goalsReceived: number;
    goalsDifference: number;
}

export interface IClub {
    date: Date;
    home_club: string;
    home_score: number;
    away_score: number;
    away_club: string;
}

export interface IFootballClub {
    wins: number;
    draws: number;
    defeats: number;
    goalsScored: number;
    goalsReceived: number;
    goalsDifference: number;
    totalMatches: number;
    points: number;
    dateCreated: string;
    clubName: string;
    clubLocation: string;
    clubManager: string;
    clubFormedYear: number;
}

export interface IMatch {
    datePlayed: string;
    awayPoints: number;
    awayScore: number;
    awayTeam: IFootballClub;
    homePoints: number;
    homeScore: number;
    homeTeam: IFootballClub;
}

