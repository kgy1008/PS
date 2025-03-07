WITH RECURSIVE GEN AS (
    SELECT ID, 1 AS GENERATION
    FROM ECOLI_DATA
    WHERE PARENT_ID IS NULL
    
    UNION ALL
    
    SELECT E.ID, GENERATION + 1 AS GENERATION
    FROM GEN G JOIN ECOLI_DATA E ON G.ID = E.PARENT_ID
)

SELECT COUNT(*) AS `COUNT`, GENERATION
FROM GEN G
WHERE G.ID NOT IN (SELECT PARENT_ID FROM ECOLI_DATA WHERE PARENT_ID IS NOT NULL)
GROUP BY GENERATION
ORDER BY GENERATION