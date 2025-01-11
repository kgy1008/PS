SELECT ID, FISH_NAME, FISH_INFO.LENGTH AS LENGTH
FROM FISH_INFO 
JOIN FISH_NAME_INFO 
ON FISH_INFO.FISH_TYPE = FISH_NAME_INFO.FISH_TYPE
WHERE FISH_INFO.FISH_TYPE IN 
(
    SELECT FISH_TYPE
    FROM FISH_INFO
    GROUP BY FISH_TYPE
    HAVING LENGTH = MAX(LENGTH)
)
ORDER BY ID