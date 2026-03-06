import { useEffect, useState } from "react";
import { getTopGroupA } from "../api/axios";

const MEDALS = ["🥇", "🥈", "🥉"];

export default function TopStudents() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    getTopGroupA()
      .then((res) => setData(res.data || []))
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p className="text-gray-500 text-sm">Đang tải...</p>;
  if (error) return <p className="text-red-600 text-sm">{error}</p>;

  return (
    <div>
      <h2 className="text-lg font-semibold text-gray-800 mb-1">
        Top 10 thí sinh khối A
      </h2>
      <p className="text-sm text-gray-500 mb-4">
        Tổng điểm Toán + Vật lý + Hóa học
      </p>

      <div className="bg-white border border-gray-200 rounded-lg overflow-hidden">
        <table className="w-full text-sm">
          <thead className="bg-gray-50">
            <tr>
              <th className="text-center px-4 py-2 font-medium text-gray-600 border-b border-gray-200 w-14">
                Hạng
              </th>
              <th className="text-left px-4 py-2 font-medium text-gray-600 border-b border-gray-200">
                Số báo danh
              </th>
              <th className="text-right px-4 py-2 font-medium text-gray-600 border-b border-gray-200">
                Toán
              </th>
              <th className="text-right px-4 py-2 font-medium text-gray-600 border-b border-gray-200">
                Vật lý
              </th>
              <th className="text-right px-4 py-2 font-medium text-gray-600 border-b border-gray-200">
                Hóa học
              </th>
              <th className="text-right px-4 py-2 font-medium text-blue-700 border-b border-gray-200">
                Tổng
              </th>
            </tr>
          </thead>
          <tbody>
            {data.map((s, i) => (
              <tr
                key={s.sbd}
                className={i % 2 === 0 ? "bg-white" : "bg-gray-50"}
              >
                <td className="text-center px-4 py-2 text-lg">
                  {MEDALS[i] ?? (
                    <span className="text-gray-400 text-sm">#{i + 1}</span>
                  )}
                </td>
                <td className="px-4 py-2 font-medium text-gray-800">{s.sbd}</td>
                <td className="px-4 py-2 text-right text-gray-700">
                  {parseFloat(s.toan).toFixed(2)}
                </td>
                <td className="px-4 py-2 text-right text-gray-700">
                  {parseFloat(s.vatLi).toFixed(2)}
                </td>
                <td className="px-4 py-2 text-right text-gray-700">
                  {parseFloat(s.hoaHoc).toFixed(2)}
                </td>
                <td className="px-4 py-2 text-right font-bold text-blue-700">
                  {parseFloat(s.totalScore).toFixed(2)}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
