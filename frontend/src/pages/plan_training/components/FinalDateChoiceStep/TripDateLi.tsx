import TripDateRange from "../../../../types/TripDateRange.ts";

type Props ={
    tripDateRange: TripDateRange,
    isConflicted: boolean
}
export const TripDateLi = ({ tripDateRange, isConflicted}: Props) => {
    return (
        <li className={`border-b-2 border-b-slate-500  text-center font-semibold rounded-sm text-slate-800 mt-3 ${isConflicted && 'bg-orange-300'}`}>
            {tripDateRange.startDate} - {tripDateRange.endDate}
        </li>
    );
};