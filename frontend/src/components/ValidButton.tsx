import {ReactNode} from "react";

type Props ={
    enabled: boolean,
    onClick: ()=>void,
    children: ReactNode
}
export const ValidButton = ({enabled, onClick, children}: Props) => {
    return (
        <button className={`py-4 text-center text-lg min-w-32 font-semibold rounded-md 
                            ${enabled ? 'bg-sky-900 text-white' : 'bg-sky-700 text-slate-400'}`}
                disabled={!enabled} onClick={onClick}
                type={"button"}>
            {children}
        </button>
    );
};