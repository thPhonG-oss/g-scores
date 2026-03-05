package com.phong.g_scores_backend.seeder;

import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private static final String CSV_PATH = "dataset/diem_thi_thpt_2024.csv";
    private static final int BATCH_SIZE = 1000;

    private final DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            if (isAlreadySeeded(conn)) {
                log.info("Data already seeded. Skipping...");
                return;
            }

            log.info("Starting data seeding from CSV...");
            long startTime = System.currentTimeMillis();

            conn.setAutoCommit(false);

            String sql = "INSERT INTO students (sbd, toan, ngu_van, ngoai_ngu, vat_li, hoa_hoc, " +
                    "sinh_hoc, lich_su, dia_li, gdcd, ma_ngoai_ngu) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ClassPathResource resource = new ClassPathResource(CSV_PATH);
            try (CSVReader csvReader = new CSVReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                // Skip header
                // header: sbd, toan, ngu_van, ngoai_ngu, vat_li, hoa_hoc, sinh_hoc, lich_su, dia_li, gdcd, ma_ngoai_ngu
                // index:  0     1     2        3          4       5        6         7        8       9     10
                String[] header = csvReader.readNext();
                if (header == null) {
                    log.warn("CSV file is empty.");
                    return;
                }

                int totalRows = 0;
                int batchCount = 0;
                String[] line;

                while ((line = csvReader.readNext()) != null) {
                    ps.setString(1, line[0].trim());           // sbd
                    setDecimalOrNull(ps, 2, line[1]);          // toan
                    setDecimalOrNull(ps, 3, line[2]);          // ngu_van
                    setDecimalOrNull(ps, 4, line[3]);          // ngoai_ngu
                    setDecimalOrNull(ps, 5, line[4]);          // vat_li
                    setDecimalOrNull(ps, 6, line[5]);          // hoa_hoc
                    setDecimalOrNull(ps, 7, line[6]);          // sinh_hoc
                    setDecimalOrNull(ps, 8, line[7]);          // lich_su
                    setDecimalOrNull(ps, 9, line[8]);          // dia_li
                    setDecimalOrNull(ps, 10, line[9]);         // gdcd
                    setStringOrNull(ps, 11,                    // ma_ngoai_ngu
                            line.length > 10 ? line[10] : null);

                    ps.addBatch();
                    totalRows++;
                    batchCount++;

                    if (batchCount >= BATCH_SIZE) {
                        ps.executeBatch();
                        conn.commit();
                        batchCount = 0;

                        if (totalRows % 100_000 == 0) {
                            log.info("Progress: {} rows...", totalRows);
                        }
                    }
                }

                if (batchCount > 0) {
                    ps.executeBatch();
                    conn.commit();
                }

                long elapsed = System.currentTimeMillis() - startTime;
                log.info("Data seeding completed: {} rows in {}s",
                        totalRows, String.format("%.1f", elapsed / 1000.0));
            }
        }
    }

    private boolean isAlreadySeeded(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM students")) {
            rs.next();
            return rs.getLong(1) > 0;
        }
    }

    private void setDecimalOrNull(PreparedStatement ps, int index, String value) throws SQLException {
        if (value == null || value.trim().isEmpty()) {
            ps.setNull(index, Types.DECIMAL);
        } else {
            ps.setBigDecimal(index, new BigDecimal(value.trim()));
        }
    }

    private void setStringOrNull(PreparedStatement ps, int index, String value) throws SQLException {
        if (value == null || value.trim().isEmpty()) {
            ps.setNull(index, Types.VARCHAR);
        } else {
            ps.setString(index, value.trim());
        }
    }
}





































