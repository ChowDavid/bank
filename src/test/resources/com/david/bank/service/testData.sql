DELETE FROM PERSON_ACCOUNT;
DELETE FROM PERSON;
DELETE FROM TRANSACTION;
DELETE FROM BANK_ACCOUNT;


INSERT INTO PERSON (ID,FIRST_NAME,LAST_NAME, USER_NAME) VALUES(1,'David','Chow','DavidChow');
INSERT INTO BANK_ACCOUNT(ACCOUNT_NUMBER,ACCOUNT_NAME,ACCOUNT_TYPE,CURRENCY) VALUES('123-2223-212','SGSaving726','SAVING','SGD');
INSERT INTO BANK_ACCOUNT(ACCOUNT_NUMBER,ACCOUNT_NAME,ACCOUNT_TYPE,CURRENCY) VALUES('123-2223-213','AUSaving726','SAVING','AUD');

INSERT INTO PERSON_ACCOUNT (PERSON_ID, ACCOUNT_NUMBER) VALUES(1,'123-2223-212');
INSERT INTO PERSON_ACCOUNT (PERSON_ID, ACCOUNT_NUMBER) VALUES(1,'123-2223-213');

INSERT INTO TRANSACTION (ACCOUNT_NUMBER,AMOUNT,VALUE_DATE) values('123-2223-212',10,'2021-1-1 00:00');
INSERT INTO TRANSACTION (ACCOUNT_NUMBER,AMOUNT,VALUE_DATE) values('123-2223-212',-1,'2021-1-1 01:00');
INSERT INTO TRANSACTION (ACCOUNT_NUMBER,AMOUNT,VALUE_DATE) values('123-2223-212',10,'2022-1-1 00:00');
INSERT INTO TRANSACTION (ACCOUNT_NUMBER,AMOUNT,VALUE_DATE) values('123-2223-212',-1,'2021-11-1 01:00');
INSERT INTO TRANSACTION (ACCOUNT_NUMBER,AMOUNT,VALUE_DATE) values('123-2223-213',10,'2021-1-1 00:00');
INSERT INTO TRANSACTION (ACCOUNT_NUMBER,AMOUNT,VALUE_DATE) values('123-2223-213',-1,'2021-1-1 01:00');
INSERT INTO TRANSACTION (ACCOUNT_NUMBER,AMOUNT,VALUE_DATE) values('123-2223-213',10,'2022-1-1 00:00');
INSERT INTO TRANSACTION (ACCOUNT_NUMBER,AMOUNT,VALUE_DATE) values('123-2223-213',-1,'2021-11-1 01:00');