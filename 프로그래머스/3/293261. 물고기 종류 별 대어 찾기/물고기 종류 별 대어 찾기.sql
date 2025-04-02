SELECT
    fi.ID,
    fni.FISH_NAME,
    fi.LENGTH AS "LENGTH"
FROM
    FISH_INFO fi
JOIN
    FISH_NAME_INFO fni
USING (FISH_TYPE)
WHERE
    (fi.FISH_TYPE, fi.LENGTH) in (
                    SELECT
                        FISH_TYPE,
                        MAX(LENGTH)
                    FROM
                        FISH_INFO
                    GROUP BY
                        FISH_TYPE
                 )
ORDER BY
   fi.ID;