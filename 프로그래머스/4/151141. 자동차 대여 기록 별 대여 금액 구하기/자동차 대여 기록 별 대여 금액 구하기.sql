WITH DURATION AS (
    SELECT H.HISTORY_ID, C.DAILY_FEE, 
        CASE 
            WHEN DATEDIFF(H.END_DATE, H.START_DATE) + 1 >= 90 THEN '90일 이상'
            WHEN DATEDIFF(H.END_DATE, H.START_DATE) + 1 >= 30 THEN '30일 이상'
            WHEN DATEDIFF(H.END_DATE, H.START_DATE) + 1 >= 7 THEN '7일 이상'
            ELSE 'NONE'
        END AS DURATION_TYPE,
        DATEDIFF(H.END_DATE, H.START_DATE) + 1 AS PERIOD
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY H JOIN CAR_RENTAL_COMPANY_CAR C ON H.CAR_ID = C.CAR_ID
    WHERE CAR_TYPE = '트럭'
)

SELECT D.HISTORY_ID, SUM(((D.PERIOD * D.DAILY_FEE) * (100 - IFNULL(P.DISCOUNT_RATE, 0))) DIV 100) AS FEE 
FROM DURATION D LEFT JOIN (SELECT * FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN WHERE CAR_TYPE = '트럭') P ON D.DURATION_TYPE = P.DURATION_TYPE
GROUP BY D.HISTORY_ID
ORDER BY FEE DESC, D.HISTORY_ID DESC