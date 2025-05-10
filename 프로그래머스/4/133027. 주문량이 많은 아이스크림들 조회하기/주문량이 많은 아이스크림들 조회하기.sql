SELECT
    f.FLAVOR
FROM (
    SELECT 
        FLAVOR,
        SUM (TOTAL_ORDER) AS "TOTAL"
    FROM
        (
            SELECT * FROM FIRST_HALF
            UNION ALL
            SELECT * FROM JULY
        ) c
    GROUP BY
        FLAVOR
    ORDER BY
        TOTAL DESC
    LIMIT 3
) f