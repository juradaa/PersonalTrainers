import TrainingTrip from "../../../../types/TrainingTrip.ts";
import React, {MouseEventHandler, SetStateAction, useEffect, useState} from "react";
import GearCategory from "../../../../types/GearCategory.ts";
import {getAllCategories, getGearForCategory} from "../../../../services/gearService.ts";
import {GearCategoryLi} from "./GearCategoryLi.tsx";
import {GearCheckbox} from "./GearCheckbox.tsx";
import {SimpleButton} from "../../../../components/SimpleButton.tsx";
import {ValidButton} from "../../../../components/ValidButton.tsx";

type Props = {
    tripData: TrainingTrip,
    setTripData: React.Dispatch<SetStateAction<TrainingTrip>>,
    setStep: React.Dispatch<SetStateAction<number>>,
    gearCategories?: GearCategory[],
    setGearCategories: React.Dispatch<SetStateAction<GearCategory[] | undefined>>,
}
export const CategoriesStep = ({tripData, setTripData, setStep, gearCategories, setGearCategories}: Props) => {

    useEffect(() => {
        if (gearCategories == undefined) {
            getAllCategories()
                .then(res => setGearCategories(res.data))
        }
    }, [])

    const [currentCategory, setCurrentCategory] = useState<number>();


    const chooseCategory = (id: number) => {
        const category = gearCategories!.filter(c => c.id === id)[0];
        if (category.gearList == undefined) {
            getGearForCategory(category.id).then(res => {
                    setGearCategories(gcs => {
                        return gcs!.map(gc => gc.id !== category.id ? gc : {...gc, gearList: res.data})
                    })
                }
            ).then(() => {
                setCurrentCategory(category.id);
            })
        } else {
            setCurrentCategory(category.id);
        }
    }


    console.log(currentCategory)
    return (
        <div className="h-full flex flex-col">
            <div className="flex gap-8 text-xl">
                <div className="grow-[1] basis-0 ">
                    <h2 className="text-2xl font-semibold">Wybierz kategorię...</h2>
                    <ul>
                        {gearCategories?.map(gc => <GearCategoryLi category={gc} onClick={chooseCategory}
                                                                   currentCategory={currentCategory} tripData={tripData}
                                                                   key={gc.id}/>)}
                    </ul>

                </div>
                <div className="grow-[1] basis-0">
                    <h2 className="text-2xl font-semibold">Oraz rodzaje sprzętu</h2>
                    <ul>
                        {currentCategory != undefined && gearCategories != undefined &&
                            gearCategories?.filter(gc => gc.id == currentCategory)[0]
                                .gearList!.map(g => <GearCheckbox tripData={tripData} setTripData={setTripData}
                                                                  gear={g}/>)}
                    </ul>
                </div>
            </div>

            <div className="mt-auto flex justify-between">
                <SimpleButton onClick={()=>setStep(s=>s-1)}>
                    Wróć
                </SimpleButton>

                <ValidButton enabled={true} onClick={()=>setStep(s=>s+1)}>
                    Dalej
                </ValidButton>
            </div>
        </div>
    );
};