import axios from "axios";
const backendUrl = import.meta.env.VITE_BACKEND_URL;
const API = axios.create({baseURL: backendUrl || "http://localhost:8080/api/"})
export default API;