SELECT
    p.ID,
    COUNT(c.ID) AS "CHILD_COUNT"
FROM
    ECOLI_DATA p
LEFT JOIN
    ECOLI_DATA c
ON
    p.ID = COALESCE (c.PARENT_ID, 0)
GROUP BY
    p.ID
ORDER BY
    p.ID





# WITH 
#     C
# AS
#     (
#         SELECT
#             PARENT_ID,
#             COUNT(*) AS CNT
#         FROM
#             ECOLI_DATA
#         GROUP BY
#             PARENT_ID
#     )
# SELECT
#     ID,
#     COALESCE(C.CNT, 0) AS CHILD_COUNT
# FROM
#     ECOLI_DATA
# LEFT JOIN
#     C
# ON
#     ID = C.PARENT_ID
# ORDER BY
#     ID