import TripDateRange from "../../../../types/TripDateRange.ts";

type Props ={
    tripDateRange: TripDateRange,
    isConflicted: boolean
}
export const TripDateLi = ({ tripDateRange, isConflicted}: Props) => {
    return (
        <li className={`border-b-2 border-b-slate-500  text-center font-semibold rounded-sm text-slate-800 mt-3 relative group ${isConflicted && 'bg-orange-300'}`}>
            {tripDateRange.startDate} - {tripDateRange.endDate}
            <div className="hidden group-hover:block  right-full top-0 w-40 p-2  absolute
             text-sm bg-slate-900 text-white">{tripDateRange.name} <div className="triangle"/> </div>
        </li>
    );
};