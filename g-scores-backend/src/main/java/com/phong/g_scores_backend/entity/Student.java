package com.phong.g_scores_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @Column(nullable = false, length = 20)
    private String sbd;

    @Column(precision = 4, scale = 2)
    private BigDecimal toan;

    @Column(name = "ngu_van", precision = 4, scale = 2)
    private BigDecimal nguVan;

    @Column(name = "ngoai_ngu", precision = 4, scale = 2)
    private BigDecimal ngoaiNgu;

    @Column(name = "vat_li", precision = 4, scale = 2)
    private BigDecimal vatLi;

    @Column(name = "hoa_hoc", precision = 4, scale = 2)
    private BigDecimal hoaHoc;

    @Column(name = "sinh_hoc", precision = 4, scale = 2)
    private BigDecimal sinhHoc;

    @Column(name = "lich_su", precision = 4, scale = 2)
    private BigDecimal lichSu;

    @Column(name = "dia_li", precision = 4, scale = 2)
    private BigDecimal diaLi;

    @Column(precision = 4, scale = 2)
    private BigDecimal gdcd;

    @Column(name = "ma_ngoai_ngu", length = 5)
    private String maNgoaiNgu;

    public BigDecimal getScoreBySubject(Subject subject) {
        return switch (subject) {
            case TOAN -> toan;
            case NGU_VAN -> nguVan;
            case NGOAI_NGU -> ngoaiNgu;
            case VAT_LI -> vatLi;
            case HOA_HOC -> hoaHoc;
            case SINH_HOC -> sinhHoc;
            case LICH_SU -> lichSu;
            case DIA_LI -> diaLi;
            case GDCD -> gdcd;
        };
    }

    public BigDecimal getTotalScoreOfGroupA() {
        if(toan == null || vatLi == null || hoaHoc == null) {
            return null;
        }
        return toan.add(vatLi).add(hoaHoc);
    }
}

