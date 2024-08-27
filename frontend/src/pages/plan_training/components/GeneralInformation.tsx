import TrainingTrip from "../../../types/TrainingTrip.ts";
import React, {ChangeEvent, RefObject, SetStateAction} from "react";
import {ValidationButton} from "../../../components/ValidationButton.tsx";
import EnhancedInputArea from "../../../components/EnhancedInputArea.tsx";

type Props = {
    tripData: TrainingTrip,
    setTripData: React.Dispatch<SetStateAction<TrainingTrip>>
    setStep: React.Dispatch<SetStateAction<number>>
    formRef: RefObject<HTMLFormElement>
}
export const GeneralInformation = ({tripData, setTripData, setStep, formRef}: Props) => {

    const onChange = (event: ChangeEvent<HTMLInputElement> | ChangeEvent<HTMLTextAreaElement>) => {
        const {name, value} = event.target;
        setTripData(tripData => {
            return {...tripData, [name]: value};
        });
        return;
    }

    const moveNext = () => {
        if (formRef && !formRef.current?.checkValidity()) {
            return
        }

        setStep(step => step + 1)
    }
    return (
        <div className="flex justify-between flex-col h-full">
            <div>
                <h2 className="text-3xl font-semibold">Basic information</h2>
                <div className="">
                    <EnhancedInputArea validationMessage={"This field is required"}
                                       labelMessage={"name"}
                                       type="text"
                                       value={tripData.name}
                                       id="name"
                                       name="name"
                                       required
                                       onChange={onChange}
                                       placeholder="eg. Calisthenics for intermediate trainees"
                    />
                    <EnhancedInputArea validationMessage={"This field is required"}
                                       labelMessage={"destination"}
                                       type="text"
                                       value={tripData.destination}
                                       id={"destination"}
                                       name={"destination"}
                                       required
                                       onChange={onChange}
                                       placeholder="eg. Egypt"
                    />
                    <EnhancedInputArea
                        isTextArea
                        validationMessage={"Description can't be shorter than 25 characters"}
                        labelMessage={"description"}
                        value={tripData.description}
                        id={"description"}
                        name={"description"}
                        required
                        onChange={onChange}
                        placeholder="eg. A trip to egypt that combines tourism with learning about training..."
                        rows={5}
                        minLength={25}
                    />
                </div>
            </div>
            <div className="flex justify-end">
                <ValidationButton enabled={true} onClick={moveNext}>
                    Next
                </ValidationButton>

            </div>
        </div>
    );
};