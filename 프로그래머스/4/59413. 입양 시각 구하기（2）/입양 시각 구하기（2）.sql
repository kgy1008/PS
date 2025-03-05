WITH RECURSIVE full_hour AS (
    SELECT 0 AS hour
    
    UNION ALL
    
    SELECT hour+1
    FROM full_hour
    WHERE hour < 23
)

SELECT h.hour, COUNT(a.DATETIME) AS COUNT
FROM full_hour h LEFT JOIN ANIMAL_OUTS a ON h.hour = HOUR(a.DATETIME)
GROUP BY h.hour
ORDER BY h.hour
