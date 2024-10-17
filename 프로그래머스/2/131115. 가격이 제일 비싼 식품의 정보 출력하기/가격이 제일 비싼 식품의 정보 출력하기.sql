-- 코드를 입력하세요
SELECT
    *
FROM
    FOOD_PRODUCT a
WHERE
    PRICE = (
        SELECT
            MAX(PRICE)
        FROM
            FOOD_PRODUCT b
    )
