import API from './API.ts'
import GearCategory from "../types/GearCategory.ts";
import Gear from "../types/Gear.ts";
import {AxiosResponse} from "axios";
export const getAllCategories = async (): Promise<AxiosResponse<GearCategory[]>> =>{
    return await API.get(`gearCategories`)
}
 export const getGearForCategory = async (id: number): Promise<AxiosResponse<Gear[]>> =>{
    return await API.get(`gearCategories/${id}/gear`)
 }
