WITH YEARLY_MAX AS (
    SELECT
        EXTRACT(YEAR FROM DIFFERENTIATION_DATE) AS "YEAR",
        MAX(SIZE_OF_COLONY) AS "YEAR_MAX"
    FROM
        ECOLI_DATA
    GROUP BY
        EXTRACT(YEAR FROM DIFFERENTIATION_DATE)
)
SELECT
    EXTRACT(YEAR FROM DIFFERENTIATION_DATE) AS "YEAR",
    YEAR_MAX - SIZE_OF_COLONY AS "YEAR_DEV",
    ID
FROM
    ECOLI_DATA og
JOIN
    YEARLY_MAX ym
WHERE
    EXTRACT(YEAR FROM DIFFERENTIATION_DATE) = ym.YEAR
ORDER BY
    YEAR,
    YEAR_DEV
