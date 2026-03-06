import { useState } from "react";
import ScoreLookup from "./components/ScoreLookup";
import Statistics from "./components/Statistics";
import TopStudents from "./components/TopStudents";

const TABS = [
  { id: "lookup", label: "Tra cứu điểm" },
  { id: "statistics", label: "Thống kê" },
  { id: "top", label: "Top 10 Khối A" },
];

export default function App() {
  const [activeTab, setActiveTab] = useState("lookup");

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Header */}
      <header className="bg-white border-b border-gray-200">
        <div className="max-w-4xl mx-auto px-4 py-4">
          <h1 className="text-2xl font-bold text-gray-800">G-Scores</h1>
          <p className="text-sm text-gray-500">Điểm thi THPT Quốc gia 2024</p>
        </div>
      </header>

      {/* Tabs */}
      <div className="bg-white border-b border-gray-200">
        <div className="max-w-4xl mx-auto px-4">
          <div className="flex gap-1">
            {TABS.map((tab) => (
              <button
                key={tab.id}
                onClick={() => setActiveTab(tab.id)}
                className={`px-4 py-3 text-sm font-medium border-b-2 transition-colors ${
                  activeTab === tab.id
                    ? "border-blue-600 text-blue-600"
                    : "border-transparent text-gray-500 hover:text-gray-700"
                }`}
              >
                {tab.label}
              </button>
            ))}
          </div>
        </div>
      </div>

      {/* Content */}
      <main className="max-w-4xl mx-auto px-4 py-6">
        {activeTab === "lookup" && <ScoreLookup />}
        {activeTab === "statistics" && <Statistics />}
        {activeTab === "top" && <TopStudents />}
      </main>
    </div>
  );
}
