import TrainingTrip from "../../../../types/TrainingTrip.ts";
import React, {SetStateAction, useState} from "react";
import {TopicItem} from "./TopicItem.tsx";
import Topic from "../../../../types/Topic.ts";
import {nanoid} from "nanoid";
import {FaCirclePlus} from "react-icons/fa6";
import {SimpleButton} from "../../../../components/SimpleButton.tsx";
import {ValidationButton} from "../../../../components/ValidationButton.tsx";

type Props = {
    tripData: TrainingTrip,
    setTripData: React.Dispatch<SetStateAction<TrainingTrip>>
    setStep: React.Dispatch<SetStateAction<number>>,
}
export const TopicStep = ({tripData, setTripData, setStep}: Props) => {
    const [newTopic, setNewTopic] = useState("");
    const addTopic = () => {
        const toAdd: Topic = {
            topic: newTopic,
            key: nanoid()
        };
        setTripData(td => {
            return {
                ...td,
                topics: td.topics.concat(toAdd)
            }
        });
        setNewTopic("");
    }


    const onKeyDown = (event : React.KeyboardEvent<HTMLInputElement>) =>{
        if(event.key === 'Enter'){
            addTopic();
        }
    }

    return (
        <div className="flex flex-col h-full">
            <h2 className="text-3xl font-semibold">Add topics (min. 1)</h2>
            <ul className="space-y-2">
                {tripData.topics.map(top => <TopicItem setTripData={setTripData} topic={top} key={top.key}/>)}
            </ul>
            <div className="flex items-center gap-6 mt-auto">
                <input className="grow-[1] border-black h-12 border-2 rounded-md"
                        type="text"
                       onChange={(event)=>{setNewTopic(event.target.value)}}
                        value={newTopic}
                        onKeyDown={onKeyDown}/>
                <button onClick={addTopic} type="button"  ><FaCirclePlus fontSize="3em" color="purple"/> </button>
            </div>
            <div className="flex justify-between mt-6">
                <SimpleButton onClick={() => setStep(s => s - 1)}>
                    Back
                </SimpleButton>

                <ValidationButton
                    enabled={ tripData.topics.length > 0 &&
                            tripData.topics.filter(t=>t.topic.trim().length >0).length == tripData.topics.length}
                    onClick={() => setStep(s => s + 1)}
                    >
                    Next
                </ValidationButton>
            </div>

        </div>
    );
};