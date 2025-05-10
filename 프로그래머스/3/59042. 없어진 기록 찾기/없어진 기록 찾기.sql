
SELECT
    f.ANIMAL_ID,
    f.NAME
FROM
    (
        SELECT
            o.ANIMAL_ID,
            o.NAME
        FROM
            ANIMAL_OUTS o
        WHERE
            o.ANIMAL_ID NOT IN
            (
                SELECT ANIMAL_ID
                FROM ANIMAL_INS
            )
    ) f
ORDER BY
    ANIMAL_ID