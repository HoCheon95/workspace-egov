-- 테이블 구조 필드를 자동으로 생성해주는 '코드 제너레이터' 스크립트
SELECT 'private ' || 
CASE
WHEN  DATA_TYPE = 'NUMBER'THEN 'Integer '
WHEN  DATA_TYPE = 'DATE'THEN 'LocalDate '
WHEN  DATA_TYPE = 'TIMESTAMP'THEN 'LocalDateTime '
ELSE 'String '
END
||
REPLACE(SUBSTR(INITCAP ('a'||COLUMN_NAME), 2),'_','') || ';'
FROM COLS
WHERE TABLE_NAME = 'PROD';



-- MyBatis 매퍼(XML)의 INSERT나 UPDATE 문에서 사용하는 파라미터 바인딩 표현식(#{...})을 자동으로 만들어주는 스크립트
SELECT '#{' ||
SUBSTR(REPLACE(INITCAP(LOWER('A'||column_name)), '_', ''),2)
|| '},'
from COLS
where TABLE_NAME = 'BUYER';




SELECT 
             
        BUYER_ID
        , BUYER_NAME
        , BUYER_BANK
        , BUYER_BANKNO
        , BUYER_BANKNAME
        , BUYER_ZIP
        , BUYER_ADD1
        , BUYER_ADD2
        , BUYER_COMTEL
        , BUYER_FAX
        , BUYER_MAIL
        , BUYER_CHARGER
        , BUYER_TELEXT
     
            , LPROD_GU "lprod.lprodGu"
            , LPROD_NAME "lprod.lprodName"
        FROM BUYER NATURAL JOIN LPROD;