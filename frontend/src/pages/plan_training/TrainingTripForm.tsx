import {useRef, useState} from "react";
import TrainingTrip from "../../types/TrainingTrip.ts";
import {GeneralInformation} from "./components/GeneralInformation.tsx";
import {TrainerChoiceStep} from "./components/TrainerChoiceStep/TrainerChoiceStep.tsx";

export const TrainingTripForm = () => {
    const formRef = useRef(null);
    const [step, setStep] = useState(1);
    const [tripData, setTripData] = useState<TrainingTrip>({
        name: "",
        description: "",
        destination: "",
        topics: [],
        startDate: undefined,
        endDate: undefined,
        shouldPublishImmediately: false,
        gearIds: new Set(),
        trainer: undefined
    })

    const steps = [<GeneralInformation tripData={tripData}
                                       setTripData={setTripData}
                                       formRef={formRef}
                                       setStep={setStep}/>,
                                <TrainerChoiceStep tripData={tripData}
                                setTripData={setTripData}
                                setStep={setStep}/>]

    return (
        <>
            <div className="grid place-content-center min-h-screen bg-slate-100">
                <div className="w-[58rem] h-[40rem] bg-white rounded-xl">
                    <form ref={formRef} className="px-24 py-16 h-full text-lg">
                        {steps[step]}
                    </form>

                </div>

            </div>
        </>
    );
};