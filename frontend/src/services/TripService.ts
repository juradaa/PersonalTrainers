import API from './API.ts'
import TrainingTrip from "../types/TrainingTrip.ts";

export const postTrip = async (trip: TrainingTrip)=>{
    delete trip.trainer!.trips;
    const toBeSent = {...trip, gearIds: Array.from(trip.gearIds), trainerId: trip.trainer!.id};
    delete toBeSent.trainer;
    toBeSent.startDate?.setTime(toBeSent.startDate!.getTime()- new Date().getTimezoneOffset()*60*1000)
    toBeSent.endDate?.setTime(toBeSent.endDate!.getTime()- new Date().getTimezoneOffset()*60*1000)


    console.log(toBeSent.startDate)
    return await API.post(`/trainingTrips`, toBeSent)
}
