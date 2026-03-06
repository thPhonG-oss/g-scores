import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || "https://localhost:8080",
  timeout: 10000,
});

api.interceptors.response.use(
  (response) => response.data,
  (error) => {
    const message =
      error.response?.data?.message || error.message || "Đã có lỗi xảy ra.";
    return Promise.reject(new Error(message));
  },
);

export const getStudentScores = (sbd) =>
  api.get(`/api/v1/students/${sbd}/scores`);
export const getScoreStatistics = () => api.get("/api/v1/scores/statistics");
export const getTopGroupA = () => api.get("/api/v1/scores/top-group-a");
