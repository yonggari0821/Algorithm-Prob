
SELECT
    c.CAR_ID,
    c.CAR_TYPE,
    TRUNCATE (c.DAILY_FEE * 30 * (1 - dp.DISCOUNT_RATE / 100), 0) AS "FEE"
FROM
    CAR_RENTAL_COMPANY_CAR c
JOIN
    CAR_RENTAL_COMPANY_DISCOUNT_PLAN dp
USING (CAR_TYPE)
WHERE
    c.CAR_TYPE IN ('세단', 'SUV')
    AND
    dp.DURATION_TYPE = '30일 이상'
    AND
    c.CAR_ID NOT IN (
        SELECT CAR_ID
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE 
            END_DATE >= '2022-11-01'
            AND
            START_DATE < '2022-12-01'
    )
    AND (c.DAILY_FEE * 30 * (1 - dp.DISCOUNT_RATE / 100)) >= 500000
    AND (c.DAILY_FEE * 30 * (1 - dp.DISCOUNT_RATE / 100)) < 2000000
ORDER BY
    FEE DESC,
    c.CAR_TYPE ASC,
    c.CAR_ID DESC
    
# 자동차 종류가 세단 또는 SUV인 자동차 중
# 2022 11 1 ~ 2022 11 30까지 대여 가능하며
# 30일간 대여 금액이 50만원 이상 200만원 미만인 자동차의
#     CAR_ID, CAR_TYPE, 대여 금액 AS "FEE"
# 대여 금액 기준 DESC, 자동차 종류 기준 ASC, 자동차 ID 기준 DESC