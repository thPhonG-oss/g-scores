package com.phong.g_scores_backend.entity;

import lombok.Getter;

@Getter
public enum Subject {
    TOAN("toan", "Toán"),
    NGU_VAN("ngu_van", "Ngữ văn"),
    NGOAI_NGU("ngoai_ngu", "Ngoại ngữ"),
    VAT_LI("vat_li", "Vật lý"),
    HOA_HOC("hoa_hoc", "Hóa học"),
    SINH_HOC("sinh_hoc", "Sinh học"),
    LICH_SU("lich_su", "Lịch sử"),
    DIA_LI("dia_li", "Địa lý"),
    GDCD("gdcd", "GDCD");

    private final String columnName;
    private final String displayName;

    Subject(String columnName, String displayName) {
        this.columnName = columnName;
        this.displayName = displayName;
    }
}
