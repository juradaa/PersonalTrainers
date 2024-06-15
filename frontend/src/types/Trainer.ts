import TripDateRange from "./TripDateRange.ts";

export default interface Trainer{
    id: number
    name: string,
    bio: string,
    alias: string,
    trips?: TripDateRange[]
}