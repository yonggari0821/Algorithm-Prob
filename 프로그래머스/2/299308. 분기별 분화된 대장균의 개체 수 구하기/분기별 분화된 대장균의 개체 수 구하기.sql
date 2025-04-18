SELECT
    DISTINCT
    CASE
        WHEN MONTH(DIFFERENTIATION_DATE) <= 3 THEN '1Q'
        WHEN MONTH(DIFFERENTIATION_DATE) <= 6 THEN '2Q'
        WHEN MONTH(DIFFERENTIATION_DATE) <= 9 THEN '3Q'
        ELSE '4Q'
    END AS QUARTER,
    COUNT(*) OVER (
        PARTITION BY 
            CASE
                WHEN MONTH(DIFFERENTIATION_DATE) <= 3 THEN '1Q'
                WHEN MONTH(DIFFERENTIATION_DATE) <= 6 THEN '2Q'
                WHEN MONTH(DIFFERENTIATION_DATE) <= 9 THEN '3Q'
                ELSE '4Q'
            END
    ) AS ECOLI_COUNT
FROM ECOLI_DATA
ORDER BY QUARTER;












# SELECT
#     CASE
#         WHEN MONTH(DIFFERENTIATION_DATE) <= 3 THEN '1Q'
#         WHEN MONTH(DIFFERENTIATION_DATE) <= 6 THEN '2Q'
#         WHEN MONTH(DIFFERENTIATION_DATE) <= 9 THEN '3Q'
#         ELSE '4Q'
#     END AS QUARTER,
#     COUNT(*) AS ECOLI_COUNT
# FROM ECOLI_DATA
# GROUP BY 
#     CASE
#         WHEN MONTH(DIFFERENTIATION_DATE) <= 3 THEN '1Q'
#         WHEN MONTH(DIFFERENTIATION_DATE) <= 6 THEN '2Q'
#         WHEN MONTH(DIFFERENTIATION_DATE) <= 9 THEN '3Q'
#         ELSE '4Q'
#     END
# ORDER BY QUARTER