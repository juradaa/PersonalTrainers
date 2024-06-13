import API from './API.ts'
import {AxiosResponse} from "axios";
import Trainer from "../types/Trainer.ts";
export const searchSenior = async (phrase: string): Promise<AxiosResponse<Trainer[]>> =>{
    return await API.get(`trainers/seniors?searched=${phrase}`)
}