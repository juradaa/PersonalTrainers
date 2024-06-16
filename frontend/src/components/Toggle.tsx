type Props = {
    onClick: ()=>void
    isActive: boolean
}


export const Toggle = ({onClick, isActive}:Props) => {

    return (
        <div className={`w-12 h-6 bg-violet-900 rounded-full shrink-0 flex p-1 items-center ${isActive && 'bg-violet-800'}`} onClick={onClick}>
            <div className={`rounded-full bg-white w-4  aspect-square transition-all ${isActive && 'translate-x-6'}`}>

            </div>
        </div>
    );
};