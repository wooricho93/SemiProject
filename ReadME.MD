CREATE TABLE Yes24 (
    Rank NUMBER(4) PRIMARY KEY, 
    Category NVARCHAR2(1000),
    Title NVARCHAR2(1000),  
    Price NVARCHAR2(20),  
    Summary VARCHAR2(4000),
    Author NVARCHAR2(1000),  
    Pub NVARCHAR2(1000),
    Grade NVARCHAR2(10)
);

CREATE SEQUENCE Yes24_sql
INCREMENT BY 1
START WITH 1;

SELECT * FROM Yes24;

DESC Yes24;

COMMIT;