import React, {SetStateAction, useEffect, useState} from "react";
import Trainer from "../../../../types/Trainer.ts";
import {searchSenior} from "../../../../services/trainerService.ts";
import {TrainerListItem} from "./TrainerListItem.tsx";
import {ChosenTrainerInfo} from "./ChosenTrainerInfo.tsx";
import TrainingTrip from "../../../../types/TrainingTrip.ts";
import {ValidButton} from "../../../../components/ValidButton.tsx";
import {SimpleButton} from "../../../../components/SimpleButton.tsx";

type Props ={
    tripData: TrainingTrip,
    setTripData: React.Dispatch<SetStateAction<TrainingTrip>>
    setStep: React.Dispatch<SetStateAction<number>>
}
export const TrainerChoiceStep = ({tripData, setTripData, setStep} : Props) => {
    const [searched, setSearched] = useState("");
    const [trainers, setTrainers] = useState<Trainer[]>();


    //debouncing
    useEffect(() => {

            console.log("here")
            const timeoutId = setTimeout(() => {
                console.log("there")
                if (searched.length > 2) {
                    searchSenior(searched)
                        .then(res => {
                            setTrainers(res.data)
                        })
                }

            }, 300);

            return () => clearTimeout(timeoutId);

        }, [searched]
    )

    const setTrainer = (trainer: Trainer) =>{
        setTripData(td=>{
        return  {...td, trainer: trainer}
        })
    }

    return (

        <div className="flex flex-col h-full">
            <input value={searched} onChange={(e) => (setSearched(e.target.value))}
                   className="rounded-md mb-8 w-full"/>
            <div className="flex h-full">
                <div className="grow-[3] basis-0">
                    {trainers == null ?
                        <p>Type at least 3 characters to see the results</p>
                        :
                        <ul className="space-y-4">
                            {trainers.map((tr, nr) => <TrainerListItem
                                key={tr.id}
                                nr={nr+1}
                                onClick={()=>{setTrainer(tr)}}
                                trainer={tr}/>)}
                        </ul>}
                </div>
                <div className="grow-[2] basis-0">
                    <ChosenTrainerInfo trainer={tripData.trainer}/>
                </div>
            </div>
            <div className="mt-auto flex justify-between">
                <SimpleButton onClick={()=>setStep(s=>s-1)}>
                    Wróć
                </SimpleButton>

                <ValidButton enabled={tripData.trainer !== undefined} onClick={()=>setStep(s=>s+1)}>
                    Dalej
                </ValidButton>
            </div>
        </div>
    );
};