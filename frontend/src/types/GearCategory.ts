import Gear from "./Gear.ts";

export default interface GearCategory{
    name: string,
    description: string,
    gearList?: Gear[]
}