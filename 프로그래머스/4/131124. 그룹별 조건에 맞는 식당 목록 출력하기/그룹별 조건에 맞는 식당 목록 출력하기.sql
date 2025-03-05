WITH REVIEW_RANK AS (
    SELECT MEMBER_ID, 
           COUNT(REVIEW_ID) AS REVIEW_COUNT
    FROM REST_REVIEW
    GROUP BY MEMBER_ID
)

SELECT MEMBER_NAME, 
    REVIEW_TEXT, 
    DATE_FORMAT(REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
FROM REST_REVIEW R
    JOIN REVIEW_RANK C ON C.MEMBER_ID = R.MEMBER_ID
    JOIN (SELECT MEMBER_ID, MEMBER_NAME FROM MEMBER_PROFILE) P ON P.MEMBER_ID = R.MEMBER_ID
WHERE REVIEW_COUNT = (SELECT MAX(REVIEW_COUNT) FROM REVIEW_RANK)
ORDER BY REVIEW_DATE, REVIEW_TEXT;