import {ReactNode} from "react";

type Props = {
    onClick: () => void,
    children: ReactNode
}
export const SimpleButton = ({onClick, children}: Props) => {
    return (
        <button className={`py-4 text-center text-lg min-w-32 font-semibold rounded-md 
                           hover:shadow-inner shadow-slate-950 text-slate-600`}
                onClick={onClick}
                type={"button"}>
            {children}
        </button>
    );
};