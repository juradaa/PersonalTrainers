import './App.css'
import {createBrowserRouter, createRoutesFromElements, Route, RouterProvider} from "react-router-dom";
import {TrainingTripForm} from "./pages/plan_training/TrainingTripForm.tsx";

function App() {
    const router = createBrowserRouter(createRoutesFromElements(
        <Route path="/">
            <Route index element={<h1>hello</h1>}/>
            <Route path="planTrip" element={<TrainingTripForm/>}/>
        </Route>
    ));
    return (
        <RouterProvider router={router}/>
    )
}

export default App
