
SELECT
    i.ANIMAL_ID,
    i.NAME
FROM
    ANIMAL_INS i
INNER JOIN
    ANIMAL_OUTS o
USING(ANIMAL_ID)
WHERE
    i.DATETIME > o.DATETIME
ORDER BY
    i.DATETIME