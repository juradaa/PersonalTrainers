import React, {SetStateAction, useEffect} from "react";
import {getTrainersFutureTrips} from "../../../../services/trainerService.ts";
import TrainingTrip from "../../../../types/TrainingTrip.ts";
import {SimpleButton} from "../../../../components/SimpleButton.tsx";
import {ValidationButton} from "../../../../components/ValidationButton.tsx";
import DatePicker, {registerLocale} from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css"
import {pl} from 'date-fns/locale/pl'
import {TripDateLi} from "./TripDateLi.tsx";
import {Toggle} from "../../../../components/Toggle.tsx";
import {postTrip} from "../../../../services/TripService.ts";
import {useNavigate} from "react-router-dom";

registerLocale('pl', pl)

type Props = {
    tripData: TrainingTrip,
    setTripData: React.Dispatch<SetStateAction<TrainingTrip>>,
    setStep: React.Dispatch<SetStateAction<number>>,

}
export const FinalDateChoiceStep = ({tripData, setTripData, setStep}: Props) => {
    const trainer = tripData.trainer!;
    const navigate = useNavigate();

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

    const handleChange = (dates: [startDate: Date | null, endDate: Date | null]) => {
        const [startDate, endDate] = dates;
        setTripData(td => {
            return {...td, startDate, endDate}
        })
    }

    const trips = trainer.trips?.map((trip => {
        const start = new Date(Date.parse(trip.startDate));
        const end = new Date(Date.parse(trip.endDate));
        const isConflicted =
            (!!tripData.startDate && !!tripData.endDate)
            && (tripData.endDate >= start && end >= tripData.startDate);
        return {...trip, isConflicted, end};
    })).sort((t1, t2) => t1.end > t2.end ? 1 : -1)

    const enabled = !!tripData.startDate &&
        !!tripData.endDate &&
        !!trips &&
        !trips.some(tr => tr.isConflicted)

    const onToggle = () => {
        setTripData(td => {
            return {
                ...td,
                shouldPublishImmediately: !td.shouldPublishImmediately
            }
        })
    }

    const submit = async () => {
        await postTrip(tripData);
        navigate("/");
    }


    return (
        <div className="h-full flex flex-col">
            <h2 className="text-3xl font-semibold mb-4">Wybierz datę</h2>
            <div className="flex gap-8 text-xl flex-col sm:flex-row">
                <div className="grow-[1]  basis-0">
                    <DatePicker
                        locale="pl"
                        dateFormat="yyyy-MM-dd"
                        minDate={new Date(Date.now())}
                        selected={tripData.startDate}
                        startDate={tripData.startDate || undefined}
                        endDate={tripData.endDate || undefined}
                        onChange={handleChange}
                        selectsRange/>
                </div>
                <div className="grow-[1] basis-0">
                    <p className="font-semibold">Trener jest zajęty w okresach:</p>
                    <p className="text-slate-700 font-semibold text-sm mt-1 mb-2">Najedź myszką na wycieczkę aby zobaczyć nazwę</p>
                    <ul className="space-y-2">
                        {trips?.map(trip => <TripDateLi tripDateRange={trip} isConflicted={trip.isConflicted} key={trip.endDate}/>)}
                    </ul>
                </div>
            </div>
            <div className="mt-auto bg-violet-100 flex justify-center items-center  gap-2 sm:gap-4 md:gap-16 text-violet-900 font-semibold p-4 mb-4">
                <p>dodaj szkic</p>
                <Toggle onClick={onToggle} isActive={tripData.shouldPublishImmediately}/>
                <p>opublikuj</p>
            </div>

            <div className=" flex justify-between">
                <SimpleButton onClick={() => setStep(s => s - 1)}>
                    Wróć
                </SimpleButton>

                <ValidationButton enabled={enabled} onClick={submit}>
                    Dalej
                </ValidationButton>
            </div>
        </div>
    );
};