import API from './API.ts'
import TrainingTrip from "../types/TrainingTrip.ts";

export const postTrip = async (trip: TrainingTrip)=>{
    delete trip.trainer!.trips;
    const toBeSent = {...trip, gearIds: Array.from(trip.gearIds), trainerId: trip.trainer!.id};
    delete toBeSent.trainer;
    return await API.post(`/trainingTrips`, toBeSent)
}
