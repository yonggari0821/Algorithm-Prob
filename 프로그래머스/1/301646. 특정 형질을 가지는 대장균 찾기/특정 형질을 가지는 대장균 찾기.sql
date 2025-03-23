SELECT
    COUNT(*) AS "COUNT"
FROM
    ECOLI_DATA
WHERE
    GENOTYPE & POWER(2, 1) != POWER(2, 1)
    AND
    (
        GENOTYPE & POWER(2, 0) = POWER(2, 0)
        OR
        GENOTYPE & POWER(2, 2) = POWER(2, 2)
    )