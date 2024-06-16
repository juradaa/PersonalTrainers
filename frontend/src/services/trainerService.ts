import API from './API.ts'
import {AxiosResponse} from "axios";
import Trainer from "../types/Trainer.ts";
import TripDateRange from "../types/TripDateRange.ts";
export const searchSenior = async (phrase: string): Promise<AxiosResponse<Trainer[]>> =>{
    return await API.get(`trainers/seniors?searched=${phrase}`);
}

export const getTrainersFutureTrips = async (id: number): Promise<AxiosResponse<TripDateRange[]>> =>{
    return await API.get(`trainers/${id}/future/trips`);
}

export const getTrainerCount = async(): Promise<AxiosResponse<number>> =>{
    return await API.get('trainers/seniors/count')
}