SELECT 
    H.HISTORY_ID,
    ROUND(
        C.DAILY_FEE * H.DATE * (
            CASE 
                WHEN H.DATE < 7 THEN 1
                WHEN H.DATE < 30 THEN 0.95
                WHEN H.DATE < 90 THEN 0.92
                ELSE 0.85
            END
        )
    ) AS FEE
FROM CAR_RENTAL_COMPANY_CAR C
JOIN (
    SELECT 
        CAR_ID, 
        HISTORY_ID, 
        DATEDIFF(END_DATE, START_DATE) + 1 AS DATE 
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
) H ON C.CAR_ID = H.CAR_ID
WHERE C.CAR_TYPE = '트럭'
ORDER BY FEE DESC, H.HISTORY_ID DESC;
