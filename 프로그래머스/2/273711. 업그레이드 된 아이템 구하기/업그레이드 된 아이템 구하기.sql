SELECT
    a.ITEM_ID, a.ITEM_NAME, a.RARITY
FROM
    ITEM_INFO a
JOIN
    (
        SELECT 
            d.ITEM_ID 
        FROM 
            ITEM_INFO b
        JOIN 
            ITEM_TREE c ON b.ITEM_ID = c.ITEM_ID
        JOIN 
            ITEM_TREE d ON c.ITEM_ID = d.PARENT_ITEM_ID
        WHERE 
            b.RARITY = 'RARE'
    ) e ON a.ITEM_ID = e.ITEM_ID
ORDER BY
    a.ITEM_ID DESC