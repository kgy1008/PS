WITH AVAILABLE AS (
    SELECT C.CAR_ID, C.CAR_TYPE, C.DAILY_FEE, H.HISTORY_ID
    FROM CAR_RENTAL_COMPANY_CAR C 
    JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY H
    ON C.CAR_ID = H.CAR_ID
    WHERE C.CAR_TYPE IN ('세단', 'SUV')
    -- 2022년 11월 1일 ~ 11월 30일 사이에 대여된 차량을 제외
    AND C.CAR_ID NOT IN (
        SELECT CAR_ID
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE START_DATE <= '2022-11-30' 
          AND END_DATE >= '2022-11-01'
    )
)

SELECT DISTINCT A.CAR_ID, A.CAR_TYPE,
    (A.DAILY_FEE * 30 * (100 - P.DISCOUNT_RATE)) DIV 100 AS FEE
FROM AVAILABLE A 
JOIN (
    SELECT * 
    FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN 
    WHERE DURATION_TYPE = '30일 이상'
    ) P ON A.CAR_TYPE = P.CAR_TYPE
GROUP BY A.HISTORY_ID
HAVING FEE >= 500000 AND FEE < 2000000
ORDER BY FEE DESC, A.CAR_TYPE, A.CAR_ID DESC