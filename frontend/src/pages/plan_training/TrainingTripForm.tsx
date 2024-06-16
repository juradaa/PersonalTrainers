import {useEffect, useRef, useState} from "react";
import TrainingTrip from "../../types/TrainingTrip.ts";
import {GeneralInformation} from "./components/GeneralInformation.tsx";
import {TrainerChoiceStep} from "./components/TrainerChoiceStep/TrainerChoiceStep.tsx";
import GearCategory from "../../types/GearCategory.ts";
import {CategoriesStep} from "./components/CategoriesStep/CategoriesStep.tsx";
import {TopicStep} from "./components/TopicsStep/TopicStep.tsx";
import {FinalDateChoiceStep} from "./components/FinalDateChoiceStep/FinalDateChoiceStep.tsx";

export const TrainingTripForm = () => {
    const formRef = useRef(null);
    const [step, setStep] = useState(0);
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
    useEffect(() => {
        document.title= "Personal Trainers - Zaplanuj Wycieczkę"
    }, []);

    const [gearCategories, setGearCategories] = useState<GearCategory[]>();

    const steps = [
        <GeneralInformation tripData={tripData}
                            setTripData={setTripData}
                            formRef={formRef}
                            setStep={setStep}/>,
        <TrainerChoiceStep tripData={tripData}
                           setTripData={setTripData}
                           setStep={setStep}/>,
        <CategoriesStep tripData={tripData}
                        setTripData={setTripData}
                        setStep={setStep}
                        gearCategories={gearCategories}
                        setGearCategories={setGearCategories}/>,
        <TopicStep tripData={tripData}
                   setTripData={setTripData}
                   setStep={setStep}/>,
        <FinalDateChoiceStep tripData={tripData}
                             setTripData={setTripData}
                             setStep={setStep}/>]


    return (
        <>
            <div className="grid place-content-center min-h-screen bg-slate-100 p-4">
                <div className="lg:w-[60rem] w-screen min-h-[40rem] bg-white rounded-xl ">
                    <form ref={formRef} className="px-4  sm:px-4 md:px-8 lg:px-24 py-8 md:py-16 h-full text-lg">
                        {steps[step]}
                    </form>
                </div>
            </div>
        </>
    );
};