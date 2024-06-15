import React, {SetStateAction, useEffect} from "react";
import {getTrainersFutureTrips} from "../../../../services/trainerService.ts";
import TrainingTrip from "../../../../types/TrainingTrip.ts";

type Props = {
    tripData: TrainingTrip,
    setTripData: React.Dispatch<SetStateAction<TrainingTrip>>
}
export const FinalDateChoiceStep = ({tripData, setTripData}: Props) => {
    const trainer = tripData.trainer!;

    useEffect(() => {
        if (trainer.trips === undefined) {
            getTrainersFutureTrips(trainer.id)
                .then(res => {
                    setTripData((td): TrainingTrip => {
                        return {
                            ...td,
                            trainer: {...td.trainer!, trips: res.data}
                        }
                    })
                })
        }
    }, [])
    return (
        <></>
    );
};