import {ReactNode} from "react";

type Props = {
    onClick: () => void,
    children: ReactNode
}
export const SimpleButton = ({onClick, children}: Props) => {
    return (
        <button className={`-px-4 md:px-0 py-2 md:py-4 text-center text-lg min-w-32 font-semibold rounded-md
                           hover:shadow-inner shadow-slate-950 text-slate-600`}
                onClick={onClick}
                type={"button"}>
            {children}
        </button>
    );
};