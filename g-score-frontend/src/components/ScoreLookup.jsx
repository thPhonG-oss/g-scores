import { useState } from "react";
import { getStudentScores } from "../api/axios";

export default function ScoreLookup() {
  const [sbd, setSbd] = useState("");
  const [student, setStudent] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleSearch = async () => {
    const trimmed = sbd.trim();
    if (!trimmed) {
      setError("Vui lòng nhập số báo danh.");
      return;
    }
    setLoading(true);
    setError("");
    setStudent(null);
    try {
      const res = await getStudentScores(trimmed);
      setStudent(res.data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-xl">
      <h2 className="text-lg font-semibold text-gray-800 mb-4">
        Tra cứu điểm theo số báo danh
      </h2>

      {/* Search */}
      <div className="flex gap-2 mb-4">
        <input
          type="text"
          className="input-field"
          placeholder="Nhập số báo danh (VD: 01000001)"
          value={sbd}
          onChange={(e) => setSbd(e.target.value)}
          onKeyDown={(e) => e.key === "Enter" && handleSearch()}
          disabled={loading}
        />
        <button
          onClick={handleSearch}
          disabled={loading}
          className="btn-primary"
        >
          {loading ? "Đang tra..." : "Tra cứu"}
        </button>
      </div>

      {error && <p className="text-red-600 text-sm mb-4">{error}</p>}

      {/* Result */}
      {student && (
        <div className="border border-gray-200 rounded-lg overflow-hidden">
          <div className="bg-gray-50 px-4 py-3 border-b border-gray-200">
            <p className="text-sm text-gray-500">Số báo danh</p>
            <p className="font-bold text-gray-800">{student.sbd}</p>
            {student.maNgoaiNgu && (
              <p className="text-sm text-gray-500 mt-1">
                Mã ngoại ngữ:{" "}
                <span className="font-medium text-gray-700">
                  {student.maNgoaiNgu}
                </span>
              </p>
            )}
          </div>

          <table className="w-full text-sm">
            <thead className="bg-gray-50">
              <tr>
                <th className="text-left px-4 py-2 font-medium text-gray-600 border-b border-gray-200">
                  Môn
                </th>
                <th className="text-right px-4 py-2 font-medium text-gray-600 border-b border-gray-200">
                  Điểm
                </th>
              </tr>
            </thead>
            <tbody>
              {Object.entries(student.scores || {}).map(
                ([subject, score], i) => (
                  <tr
                    key={subject}
                    className={i % 2 === 0 ? "bg-white" : "bg-gray-50"}
                  >
                    <td className="px-4 py-2 text-gray-700">{subject}</td>
                    <td className="px-4 py-2 text-right font-medium text-gray-800">
                      {parseFloat(score).toFixed(2)}
                    </td>
                  </tr>
                ),
              )}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
