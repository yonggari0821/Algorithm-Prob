
SELECT
    EXTRACT(HOUR FROM DATETIME) AS HOUR,
    COUNT(*) AS "COUNT"
FROM
    ANIMAL_OUTS
WHERE
    EXTRACT(HOUR FROM DATETIME) >= 9
    AND
    EXTRACT(HOUR FROM DATETIME) < 20
GROUP BY
    `HOUR`
ORDER BY
    `HOUR`