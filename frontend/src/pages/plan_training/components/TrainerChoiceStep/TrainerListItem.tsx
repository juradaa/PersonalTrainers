import Trainer from "../../../../types/Trainer.ts";

type Props = {
    trainer: Trainer,
    nr: number,
    onClick: () => void
}
export const TrainerListItem = ({nr, trainer, onClick}: Props) => {
    return (
        <li  onClick={onClick} className="group">
            <div className="bg-violet-700 text-white mr-4
                        inline-grid place-content-center
                        aspect-square rounded-full w-8 group-hover:scale-125 transition-all ">
                {nr}
            </div>
            <p className="inline">{trainer.name}</p> <p className="ml-4 inline italic">{trainer.alias}</p>
        </li>
    );
};