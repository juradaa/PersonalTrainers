import Gear from "./Gear.ts";

export default interface GearCategory{
    id: number,
    name: string,
    description: string,
    gearList?: Gear[]
}