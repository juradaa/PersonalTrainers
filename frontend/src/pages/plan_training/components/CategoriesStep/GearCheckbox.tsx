import TrainingTrip from "../../../../types/TrainingTrip.ts";
import React, {SetStateAction} from "react";
import Gear from "../../../../types/Gear.ts";

type Props = {
    tripData: TrainingTrip,
    setTripData: React.Dispatch<SetStateAction<TrainingTrip>>,
    gear: Gear
}
export const GearCheckbox = ({tripData, setTripData, gear}: Props) => {

    const onClick = ()=>{
        setTripData(td=>{
            const newSet = new Set(td.gearIds);
            if(td.gearIds.has(gear.id)){
                newSet.delete(gear.id);
            }else{
                newSet.add(gear.id)
            }
            return {...td, gearIds: newSet}
        })
    }

    return (
        <div className="has-[:checked]:border-purple-700 has-[:checked]:bg-slate-100
                        border-black border-2 my-4 rounded-md p-4
                        flex items-center gap-4">
            <input
                type="checkbox"
                onChange={onClick}
                checked={tripData.gearIds.has(gear.id)}
                className="w-6 h-6 rounded-md checked:text-purple-700"
            />
            <p className="block">
                {gear.name}
            </p>

        </div>

    );
};