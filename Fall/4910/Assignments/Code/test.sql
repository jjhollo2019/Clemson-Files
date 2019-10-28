INSERT INTO SPONSOR
VALUES ('S1', 'WES1', 'WES1@MAIL.COM');
INSERT INTO SPONSOR
VALUES ('S2', 'WES2', 'WES2@MAIL.COM');
INSERT INTO SPONSOR
VALUES ('S3', 'WES3', 'WES3@MAIL.COM');
INSERT INTO SPONSOR
VALUES ('S4', 'WES4', 'WES4@MAIL.COM');
INSERT INTO SPONSOR
VALUES ('S5', 'WES5', 'WES5@MAIL.COM');

SELECT * FROM SPONSOR;
SELECT * FROM SPONSOR WHERE NAME = 'WES2';

INSERT INTO USERNAMES
VALUES ('S1', 'TEST1', 'TEST1');
INSERT INTO USERNAMES
VALUES ('S2', 'TEST2', 'TEST2');
INSERT INTO USERNAMES
VALUES ('S3', 'TEST3', 'TEST3');

SELECT * FROM USERNAMES;
SELECT * FROM USERNAMES WHERE SPONSOR_ID = 'S3';

INSERT INTO MARKETPLACE
VALUES ('M1', 'S1');
INSERT INTO MARKETPLACE
VALUES ('M2', 'S2');
INSERT INTO MARKETPLACE
VALUES ('M3', 'S3');
INSERT INTO MARKETPLACE
VALUES ('M4', 'S4');
INSERT INTO MARKETPLACE
VALUES ('M5', 'S5');

SELECT * FROM MARKETPLACE;
SELECT * FROM MARKETPLACE WHERE SPONSOR = 'S2';

INSERT INTO ADMINISTRATIVE
VALUES ('A1', 'FIRST1', 'M', 'LAST1', 'WES1@EMAIL.COM');
INSERT INTO ADMINISTRATIVE
VALUES ('A2', 'FIRST2', 'N', 'LAST2', 'WES2@EMAIL.COM');
INSERT INTO ADMINISTRATIVE
VALUES ('A3', 'FIRST3', 'O', 'LAST3', 'WES3@EMAIL.COM');
INSERT INTO ADMINISTRATIVE
VALUES ('A4', 'FIRST4', 'P', 'LAST4', 'WES4@EMAIL.COM');
INSERT INTO ADMINISTRATIVE
VALUES ('A5', 'FIRST5', 'Q', 'LAST5', 'WES5@EMAIL.COM');

SELECT * FROM ADMINISTRATIVE;
SELECT * FROM ADMINISTRATIVE WHERE FNAME = 'FIRST2';

INSERT INTO DRIVER
VALUES (000000001, 'FIRST1', 'M', 'LAST1', 'U1', 'PASSWORD1', 'WES1@EMAIL.COM', 'S1', 100, 'M1', 'ORDERHISTORY1');
INSERT INTO DRIVER
VALUES (000000002, 'FIRST2', 'N', 'LAST2', 'U2', 'PASSWORD2', 'WES2@EMAIL.COM', 'S2', 101, 'M2', 'ORDERHISTORY2');
INSERT INTO DRIVER
VALUES (000000003, 'FIRST3', 'O', 'LAST3', 'U3', 'PASSWORD3', 'WES3@EMAIL.COM', 'S3', 102, 'M3', 'ORDERHISTORY3');
INSERT INTO DRIVER
VALUES (000000004, 'FIRST4', 'P', 'LAST4', 'U4', 'PASSWORD4', 'WES4@EMAIL.COM', 'S4', 103, 'M4', 'ORDERHISTORY4');
INSERT INTO DRIVER
VALUES (000000005, 'FIRST5', 'Q', 'LAST5', 'U5', 'PASSWORD5', 'WES5@EMAIL.COM', 'S5', 104, 'M5', 'ORDERHISTORY5');

SELECT * FROM DRIVER;
SELECT * FROM DRIVER WHERE FNAME = 'FIRST2';

INSERT INTO CAN_VIEW
VALUES ('S1', 000000001, 'M1');
INSERT INTO CAN_VIEW
VALUES ('S2', 000000002, 'M2');
INSERT INTO CAN_VIEW
VALUES ('S3', 000000003, 'M3');
INSERT INTO CAN_VIEW
VALUES ('S4', 000000004, 'M4');
INSERT INTO CAN_VIEW
VALUES ('S5', 000000005, 'M5');

SELECT * FROM CAN_VIEW;
SELECT * FROM CAN_VIEW WHERE SPONSOR = 'S4';

INSERT INTO USERNAMES
VALUES ('S1', 'USERNAME1');
INSERT INTO USERNAMES
VALUES ('S2', 'USERNAME2');
INSERT INTO USERNAMES
VALUES ('S3', 'USERNAME3');
INSERT INTO USERNAMES
VALUES ('S4', 'USERNAME4');
INSERT INTO USERNAMES
VALUES ('S5', 'USERNAME5');

SELECT * FROM USERNAMES;
SELECT * FROM USERNAMES WHERE USERNAMES = 'U2';

INSERT INTO CAN_MODIFY_SPONSOR
VALUES ('A1', 'S1');
INSERT INTO CAN_MODIFY_SPONSOR
VALUES ('A2', 'S2');
INSERT INTO CAN_MODIFY_SPONSOR
VALUES ('A3', 'S3');
INSERT INTO CAN_MODIFY_SPONSOR
VALUES ('A4', 'S4');
INSERT INTO CAN_MODIFY_SPONSOR
VALUES ('A5', 'S5');

SELECT * FROM CAN_MODIFY_SPONSOR;
SELECT * FROM CAN_MODIFY_SPONSOR WHERE SPONSOR_ID = 'S2';

INSERT INTO CAN_MODIFY_DRIVER
VALUES ('A1', 000000001);
INSERT INTO CAN_MODIFY_DRIVER
VALUES ('A2', 000000002);
INSERT INTO CAN_MODIFY_DRIVER
VALUES ('A3', 000000003);
INSERT INTO CAN_MODIFY_DRIVER
VALUES ('A4', 000000004);
INSERT INTO CAN_MODIFY_DRIVER
VALUES ('A5', 000000005);

SELECT * FROM CAN_MODIFY_DRIVER;
SELECT * FROM CAN_MODIFY_DRIVER WHERE SSN = 000000005;

INSERT INTO CAN_MODIFY_MARKETPLACE
VALUES ('A1', 'M1');
INSERT INTO CAN_MODIFY_MARKETPLACE
VALUES ('A2', 'M2');
INSERT INTO CAN_MODIFY_MARKETPLACE
VALUES ('A3', 'M3');
INSERT INTO CAN_MODIFY_MARKETPLACE
VALUES ('A4', 'M4');
INSERT INTO CAN_MODIFY_MARKETPLACE
VALUES ('A5', 'M5');

SELECT * FROM CAN_MODIFY_MARKETPLACE;
SELECT * FROM CAN_MODIFY_MARKETPLACE WHERE MARKET_ID = 'M2';

INSERT INTO ADD_PRODUCTS
VALUES ('M1', 'S1', 'PRODUCTLIST1');
INSERT INTO ADD_PRODUCTS
VALUES ('M2', 'S2', 'PRODUCTLIST2');
INSERT INTO ADD_PRODUCTS
VALUES ('M3', 'S3', 'PRODUCTLIST3');
INSERT INTO ADD_PRODUCTS
VALUES ('M4', 'S4', 'PRODUCTLIST4');
INSERT INTO ADD_PRODUCTS
VALUES ('M5', 'S5', 'PRODUCTLIST5');

SELECT * FROM ADD_PRODUCTS;
SELECT * FROM ADD_PRODUCTS WHERE SPONSOR_ID = 'S2';

INSERT INTO PRODUCTS
VALUES ('M1', 'PRODUCTS1');
INSERT INTO PRODUCTS
VALUES ('M2', 'PRODUCTS2');
INSERT INTO PRODUCTS
VALUES ('M3', 'PRODUCTS3');
INSERT INTO PRODUCTS
VALUES ('M4', 'PRODUCTS4');
INSERT INTO PRODUCTS
VALUES ('M5', 'PRODUCTS5');

SELECT * FROM PRODUCTS;
SELECT * FROM PRODUCTS WHERE PRODUCT = 'PRODUCTS2';
