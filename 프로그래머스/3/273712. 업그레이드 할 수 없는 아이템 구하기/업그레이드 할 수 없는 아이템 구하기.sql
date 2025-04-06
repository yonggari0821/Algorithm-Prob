SELECT
    ITEM_ID,
    ITEM_NAME,
    RARITY
FROM
    ITEM_INFO
WHERE
    ITEM_ID NOT IN (
        SELECT COALESCE(PARENT_ITEM_ID, 0)
        FROM ITEM_TREE
    )
ORDER BY
    ITEM_ID DESC;