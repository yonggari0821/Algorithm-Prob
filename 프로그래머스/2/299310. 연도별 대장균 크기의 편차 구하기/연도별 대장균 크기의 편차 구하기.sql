SELECT
    #  분화되어 나온 날짜의 연도
    YEAR(DIFFERENTIATION_DATE) AS YEAR, 
    # 개체 크기를 분화되어 나온 날짜의 연도를 기준으로 PARTITION BY한 걸 기반으로 최대값을 구해서 거기서 개체 크기를 빼서 YEAR_DEV로
    ( MAX(SIZE_OF_COLONY) 
        OVER 
            (PARTITION BY YEAR(DIFFERENTIATION_DATE)) 
    ) - SIZE_OF_COLONY
    AS YEAR_DEV, 
    ID
FROM 
    ECOLI_DATA
ORDER BY 
    YEAR, 
    YEAR_DEV