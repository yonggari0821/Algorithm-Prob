SELECT
    NAME,
    DATETIME
FROM
    ANIMAL_INS
WHERE
    ANIMAL_ID NOT IN
    (
        SELECT
            ANIMAL_ID
        FROM
            ANIMAL_INS
        INNER JOIN
            ANIMAL_OUTS
        USING(ANIMAL_ID)
    )
ORDER BY
    DATETIME
LIMIT 3