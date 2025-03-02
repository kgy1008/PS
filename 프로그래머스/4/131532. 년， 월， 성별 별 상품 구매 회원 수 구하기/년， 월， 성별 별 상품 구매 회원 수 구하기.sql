-- 코드를 입력하세요
SELECT YEAR(SALES_DATE) AS YEAR, 
    MONTH(SALES_DATE) AS MONTH, 
    GENDER, 
    COUNT(DISTINCT U.USER_ID) AS USERS
FROM ONLINE_SALE O JOIN  USER_INFO U ON U.USER_ID = O.USER_ID
WHERE GENDER IS NOT NULL
GROUP BY YEAR, MONTH, GENDER
ORDER BY YEAR, MONTH, GENDER;