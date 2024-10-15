
# 2022년도 평가 점수가 가장 높은 사원들의 점수, 사번, 성명, 직책, 이메일

# WITH numAndScore AS (
    SELECT
        SUM(SCORE) AS 'SCORE',
        EMP_NO,
        EMP_NAME,
        POSITION,
        EMAIL
    FROM
        HR_EMPLOYEES-- 사원 정보
    JOIN
        HR_GRADE -- 사원 평가 정보
    USING
        (EMP_NO)
    GROUP BY
        EMP_NO
    ORDER BY
        SCORE DESC
    LIMIT 1
# )
    
