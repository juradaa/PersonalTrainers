import React, {ChangeEvent, SetStateAction} from "react";
import TrainingTrip from "../../../../types/TrainingTrip.ts";
import Topic from "../../../../types/Topic.ts";
import {ImBin} from "react-icons/im";

type Props = {
    setTripData: React.Dispatch<SetStateAction<TrainingTrip>>,
    topic: Topic
}
export const TopicItem = ({setTripData, topic}: Props) => {
    const onChange = (event: ChangeEvent<HTMLInputElement>) => {
        const {value} = event.target
        setTripData(td => {
            return {
                ...td,
                topics: td.topics.map(t => t.key === topic.key ? {...t, topic: value} : t)
            }
        })
    }

    const onDelete = () => {
        setTripData(td => {
            return {
                ...td,
                topics: td.topics.filter(t => t.key !== topic.key)
            }
        })
    }

    return (
        <li className="flex outline-none gap-6 items-center border-b-2 border-gray-600 py-2 relative">
            {topic.topic.length == 0 &&
                <div className="absolute top-0 right-12 text-red-700">Field cannot be empty</div>}
            <input
                className="grow-[1] border-none border-b-2"
                value={topic.topic}
                onChange={onChange}
            />
            <div className="scale-150" onClick={onDelete}><ImBin/></div>
        </li>
    );
};