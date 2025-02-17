import GearCategory from "../../../../types/GearCategory.ts";
import TrainingTrip from "../../../../types/TrainingTrip.ts";

type Props = {
    category: GearCategory,
    onClick: (id: number)=>void,
    currentCategory: number | undefined,
    tripData: TrainingTrip
}
export const GearCategoryLi = ({category, currentCategory, onClick, tripData}: Props) => {
    const isChosen = category.id === currentCategory;
    const chosenGear = category.gearList?.filter(g => tripData.gearIds.has(g.id))


    return (
        <li onClick={()=>onClick(category.id)} className={`  border-2 flex justify-between rounded-md p-1 my-1  sm:p-4 max-w-none
                                                sm:my-4 align-baseline  gap-3 mr-3 text-md sm:text-lg items-center
                                            ${isChosen ? 'border-violet-700 bg-violet-100' : 'border-black'}`}>
            <p className="block">{category.name}</p> <p className="block text-xl text-violet-700 font font-semibold scale-110 mr-2">{chosenGear?.length != 0 && chosenGear?.length}</p>
        </li>
    );
};