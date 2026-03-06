import { useEffect, useState } from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";
import { getScoreStatistics } from "../api/axios";

const LEVELS = [
  { key: "excellent", label: "≥ 8", color: "#16a34a" },
  { key: "good", label: "6 – 8", color: "#2563eb" },
  { key: "average", label: "4 – 6", color: "#d97706" },
  { key: "poor", label: "< 4", color: "#dc2626" },
];

export default function Statistics() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    getScoreStatistics()
      .then((res) => {
        setData(
          (res.data || []).map((item) => ({
            name: item.subject,
            excellent: item.excellent,
            good: item.good,
            average: item.average,
            poor: item.poor,
          })),
        );
      })
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p className="text-gray-500 text-sm">Đang tải...</p>;
  if (error) return <p className="text-red-600 text-sm">{error}</p>;

  return (
    <div>
      <h2 className="text-lg font-semibold text-gray-800 mb-4">
        Thống kê điểm thi theo môn
      </h2>

      {/* Chart */}
      <div className="bg-white border border-gray-200 rounded-lg p-4 mb-4">
        <ResponsiveContainer width="100%" height={350}>
          <BarChart
            data={data}
            margin={{ top: 5, right: 20, left: 10, bottom: 60 }}
          >
            <CartesianGrid strokeDasharray="3 3" stroke="#f0f0f0" />
            <XAxis
              dataKey="name"
              tick={{ fontSize: 12 }}
              angle={-35}
              textAnchor="end"
              interval={0}
            />
            <YAxis
              tick={{ fontSize: 11 }}
              tickFormatter={(v) =>
                v >= 1000 ? `${(v / 1000).toFixed(0)}k` : v
              }
            />
            <Tooltip
              formatter={(value, name) => [
                value.toLocaleString(),
                LEVELS.find((l) => l.key === name)?.label,
              ]}
            />
            <Legend
              formatter={(value) => LEVELS.find((l) => l.key === value)?.label}
              wrapperStyle={{ paddingTop: 12 }}
            />
            {LEVELS.map((l) => (
              <Bar
                key={l.key}
                dataKey={l.key}
                fill={l.color}
                radius={[2, 2, 0, 0]}
              />
            ))}
          </BarChart>
        </ResponsiveContainer>
      </div>

      {/* Table */}
      <div className="bg-white border border-gray-200 rounded-lg overflow-hidden">
        <table className="w-full text-sm">
          <thead className="bg-gray-50">
            <tr>
              <th className="text-left px-4 py-2 font-medium text-gray-600 border-b border-gray-200">
                Môn học
              </th>
              <th className="text-right px-4 py-2 font-medium text-green-700 border-b border-gray-200">
                ≥ 8
              </th>
              <th className="text-right px-4 py-2 font-medium text-blue-700 border-b border-gray-200">
                6 – 8
              </th>
              <th className="text-right px-4 py-2 font-medium text-yellow-700 border-b border-gray-200">
                4 – 6
              </th>
              <th className="text-right px-4 py-2 font-medium text-red-700 border-b border-gray-200">
                &lt; 4
              </th>
              <th className="text-right px-4 py-2 font-medium text-gray-600 border-b border-gray-200">
                Tổng
              </th>
            </tr>
          </thead>
          <tbody>
            {data.map((row, i) => {
              const total = row.excellent + row.good + row.average + row.poor;
              return (
                <tr
                  key={row.name}
                  className={i % 2 === 0 ? "bg-white" : "bg-gray-50"}
                >
                  <td className="px-4 py-2 text-gray-700">{row.name}</td>
                  <td className="px-4 py-2 text-right text-green-700">
                    {row.excellent.toLocaleString()}
                  </td>
                  <td className="px-4 py-2 text-right text-blue-700">
                    {row.good.toLocaleString()}
                  </td>
                  <td className="px-4 py-2 text-right text-yellow-700">
                    {row.average.toLocaleString()}
                  </td>
                  <td className="px-4 py-2 text-right text-red-700">
                    {row.poor.toLocaleString()}
                  </td>
                  <td className="px-4 py-2 text-right font-medium text-gray-700">
                    {total.toLocaleString()}
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
}
