import Trainer from "../../../../types/Trainer.ts";
import {useNavigate} from "react-router-dom";

type Props = {
    trainer?: Trainer
}
export const ChosenTrainerInfo = ({trainer}: Props) => {
    const navigate = useNavigate();
    if(trainer == undefined){
        return <div className="bg-slate-50 text-slate-900 p-4">
            <p className="mb-6 text-xl" >Choose a trainer or:</p>
            <button type="button"
                    className="font-semibold bg-slate-200 mx-auto block px-4 py-2 rounded-md"
                    onClick={()=>navigate("/")}>Cancel</button>

        </div>
    }

    return (
        <div className="rounded-md bg-violet-100 p-4 font-xl">
            <p className="text-violet-900 font-semibold">Chosen trainer:</p>
            <p className="font-semibold mt-2">{trainer.name}</p>
            <p className="italic mb-2">{trainer.alias}</p>
            <p className="font-lg  maxlines-10">
                {trainer.bio}
            </p>
        </div>
    );
};