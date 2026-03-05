CREATE TABLE IF NOT EXISTS students (
    sbd         VARCHAR(20)     PRIMARY KEY,
    toan        DECIMAL(4,2)    NULL,
    ngu_van     DECIMAL(4,2)    NULL,
    ngoai_ngu   DECIMAL(4,2)    NULL,
    vat_li      DECIMAL(4,2)    NULL,
    hoa_hoc     DECIMAL(4,2)    NULL,
    sinh_hoc    DECIMAL(4,2)    NULL,
    lich_su     DECIMAL(4,2)    NULL,
    dia_li      DECIMAL(4,2)    NULL,
    gdcd        DECIMAL(4,2)    NULL,
    ma_ngoai_ngu VARCHAR(5)     NULL
);
