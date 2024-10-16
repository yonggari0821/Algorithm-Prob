# SELECT
#     MCDP_CD AS "진료과코드",
#     COUNT(*) AS "5월예약건수"
# FROM
#     APPOINTMENT
# WHERE
#     EXTRACT(YEAR FROM APNT_YMD) = 2022
#     AND EXTRACT(MONTH FROM APNT_YMD) = 5
# GROUP BY
#     MCDP_CD
# ORDER BY
#     "5월예약건수" ASC,
#     "진료과코드" ASC


# SELECT
#     MCDP_CD AS "진료과코드",
#     COUNT(*) AS "5월예약건수"
# FROM
#     APPOINTMENT
# WHERE
#     APNT_YMD BETWEEN '2022-05-01' AND '2022-05-31'
# GROUP BY
#     MCDP_CD
# ORDER BY
#     "5월예약건수" ASC,
#     "진료과코드" ASC

SELECT
    MCDP_CD AS '진료과코드',
    COUNT(*) AS '5월예약건수'
FROM
    APPOINTMENT
WHERE
    DATE_FORMAT(APNT_YMD, '%Y-%m') = '2022-05'
GROUP BY
    MCDP_CD
ORDER BY
    COUNT(*) ASC,
    MCDP_CD ASC