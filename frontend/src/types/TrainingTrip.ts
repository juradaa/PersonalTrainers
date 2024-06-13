import Trainer from "./Trainer.ts";

export default interface TrainingTrip{
    name: string,
    description: string,
    destination: string,
    topics: string[],
    startDate?: Date,
    endDate?: Date,
    shouldPublishImmediately: boolean,
    gearIds: Set<number>,
    trainer?: Trainer

}
