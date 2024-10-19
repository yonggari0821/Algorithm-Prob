SELECT
    ITEM_ID,
    ITEM_NAME,
    RARITY
FROM
    ITEM_INFO
WHERE
    ITEM_ID IN (
        SELECT
            ITEM_ID
        FROM
            ITEM_TREE
        WHERE
            PARENT_ITEM_ID IN (
                SELECT
                    ITEM_ID
                FROM
                    ITEM_INFO
                WHERE
                    RARITY = 'RARE'
            )
    )
ORDER BY
    ITEM_ID DESC