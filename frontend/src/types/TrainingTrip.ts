import Trainer from "./Trainer.ts";
import Topic from "./Topic.ts";

export default interface TrainingTrip{
    name: string,
    description: string,
    destination: string,
    topics: Topic[],
    startDate?: Date | null,
    endDate?: Date | null,
    shouldPublishImmediately: boolean,
    gearIds: Set<number>,
    trainer?: Trainer

}
