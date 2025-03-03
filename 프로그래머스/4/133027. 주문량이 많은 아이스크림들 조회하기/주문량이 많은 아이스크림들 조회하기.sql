WITH JULY_SALE AS (
    SELECT FLAVOR, SUM(TOTAL_ORDER) AS TOTAL_ORDER
    FROM JULY
    GROUP BY FLAVOR
),
FIRST_HALF_SALE AS (
    SELECT FLAVOR, SUM(TOTAL_ORDER) AS TOTAL_ORDER
    FROM FIRST_HALF
    GROUP BY FLAVOR
)

SELECT F.FLAVOR 
FROM FIRST_HALF_SALE F LEFT JOIN JULY_SALE J ON F.FLAVOR = J.FLAVOR
ORDER BY F.TOTAL_ORDER + J.TOTAL_ORDER DESC
LIMIT 3
