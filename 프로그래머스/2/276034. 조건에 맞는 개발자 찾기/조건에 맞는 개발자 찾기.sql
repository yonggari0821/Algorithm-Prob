# Python이나 C# 스킬을 가진 개발자의 정보를 조회하려 합니다. 
# 개발자의 ID, 이메일, 이름, 성을 조회하는 SQL 문을 작성해 주세요.
# 결과는 ID를 기준으로 오름차순 정렬해 주세요.

SELECT
    ID,
    EMAIL,
    FIRST_NAME,
    LAST_NAME
FROM
    DEVELOPERS
WHERE
    SKILL_CODE & (
        SELECT
            CODE
        FROM
            SKILLCODES
        WHERE
            NAME LIKE "Python"
    )
    > 0
    OR
    SKILL_CODE & (
        SELECT
            CODE
        FROM
            SKILLCODES
        WHERE
            NAME LIKE "C#"
    )
    > 0
ORDER BY
    ID