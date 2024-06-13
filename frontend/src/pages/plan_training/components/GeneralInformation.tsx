import TrainingTrip from "../../../types/TrainingTrip.ts";
import React, {ChangeEvent, RefObject, SetStateAction} from "react";
import {EnhancedInput} from "../../../components/EnhancedInput.tsx";
import {FormButton} from "../../../components/FormButton.tsx";
import {EnhancedTextArea} from "../../../components/EnhancedTextArea.tsx";

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
                <h2 className="text-3xl font-semibold">Podstawowe informacje</h2>
                <div className="">
                    <EnhancedInput validationMessage={"To pole jest obowiązkowe"}
                                   labelMessage={"nazwa:"}
                                   type="text"
                                   value={tripData.name}
                                   id="name"
                                   name="name"
                                   required
                                   onChange={onChange}
                                   placeholder="Np. Warsztaty kalistenicze w Dąbrowie Górniczej"
                    />
                    <EnhancedInput validationMessage={"To pole jest obowiązkowe"}
                                   labelMessage={"destynacja"}
                                   type="text"
                                   value={tripData.destination}
                                   id={"destination"}
                                   name={"destination"}
                                   required
                                   onChange={onChange}
                                   placeholder="Np. Dąbrowa górnicza"
                    />
                    <EnhancedTextArea validationMessage={"Minimalna długość to 25 znaków"}
                                   labelMessage={"opis"}
                                   value={tripData.description}
                                   id={"description"}
                                   name={"description"}
                                   required
                                   onChange={onChange}
                                   placeholder="Ważne rzeczy"
                                      rows={5}
                                      minLength={25}
                    />
                </div>
            </div>
            <div className="flex justify-end">
                <FormButton enabled={true} onClick={moveNext}>
                    Dalej
                </FormButton>

            </div>
        </div>
    );
};