import {ReactNode} from "react";

type Props ={
    enabled: boolean,
    onClick: ()=>void,
    children: ReactNode
    className?: string
}
export const ValidationButton = ({enabled, onClick, children, className}: Props) => {
    return (
        <button className={`py-4 text-center text-lg min-w-32 font-semibold rounded-md hover:shadow-inner
                            ${enabled ? 'bg-sky-900 text-white' : 'bg-sky-700 text-slate-400'} ${className}`}
                disabled={!enabled} onClick={onClick}
                type={"button"}>
            {children}
        </button>
    );
};