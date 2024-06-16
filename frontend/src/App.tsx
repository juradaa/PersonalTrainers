import './App.css'
import {createBrowserRouter, createRoutesFromElements, Route, RouterProvider} from "react-router-dom";
import {TrainingTripForm} from "./pages/plan_training/TrainingTripForm.tsx";
import {IndexPage} from "./pages/index/IndexPage.tsx";

function App() {
    const router = createBrowserRouter(createRoutesFromElements(
        <Route path="/">
            <Route index element={<IndexPage/>}/>
            <Route path="planTrip" element={<TrainingTripForm/>}/>
        </Route>
    ));
    return (
        <RouterProvider router={router}/>
    )
}

export default App
