CREATE TABLE SPONSOR (
    SPONSOR_ID VARCHAR(10) NOT NULL,
    NAME VARCHAR(30) NOT NULL,
    EMAIL VARCHAR(50) NOT NULL,
    PRIMARY KEY (SPONSOR_ID)
);

CREATE TABLE MARKETPLACE (
    MARKET_ID VARCHAR(10) NOT NULL,
    SPONSOR VARCHAR(30) NOT NULL,
    PRIMARY KEY (MARKET_ID)
);

CREATE TABLE ADMINISTRATIVE (
    ID VARCHAR(10) NOT NULL,
    FNAME VARCHAR(25) NOT NULL,
    MINIT CHAR,
    LNAME VARCHAR(50) NOT NULL,
    EMAIL VARCHAR(20),
    PRIMARY KEY (ID)
);

CREATE TABLE DRIVER (
    SSN INTEGER(9) NOT NULL,
    FNAME VARCHAR(25) NOT NULL,
    MINIT CHAR,
    LNAME VARCHAR(50) NOT NULL,
    USERNAME VARCHAR(50),
    PASSWORD VARCHAR(25),
    EMAIL VARCHAR(20),
    SPONSOR VARCHAR(30) NOT NULL,
    POINT_TOTAL INTEGER(4) NOT NULL,
    MARKET VARCHAR(10) NOT NULL,
    ORDER_HISTORY VARCHAR(50),
    PRIMARY KEY (SSN),
    FOREIGN KEY (SPONSOR) REFERENCES SPONSOR(SPONSOR_ID),
    FOREIGN KEY (MARKET) REFERENCES MARKETPLACE(MARKET_ID)
);

CREATE TABLE USERNAMES (
    SPONSOR_ID VARCHAR(10) NOT NULL,
    USERNAMES VARCHAR(50) NOT NULL,
    PRIMARY KEY (SPONSOR_ID, USERNAMES),
    FOREIGN KEY (SPONSOR_ID) REFERENCES SPONSOR(SPONSOR_ID)
);

CREATE TABLE CAN_MODIFY_SPONSOR (
    ID VARCHAR(10) NOT NULL,
    SPONSOR_ID VARCHAR(10) NOT NULL,
    PRIMARY KEY (ID, SPONSOR_ID),
    FOREIGN KEY (SPONSOR_ID) REFERENCES SPONSOR(SPONSOR_ID),
    FOREIGN KEY (ID) REFERENCES ADMINISTRATIVE(ID)
);

CREATE TABLE CAN_MODIFY_DRIVER (
    ID VARCHAR(10) NOT NULL,
    SSN INTEGER(9) NOT NULL,
    PRIMARY KEY (ID, SSN),
    FOREIGN KEY (SSN) REFERENCES DRIVER(SSN),
    FOREIGN KEY (ID) REFERENCES ADMINISTRATIVE(ID)
);

CREATE TABLE CAN_MODIFY_MARKETPLACE (
    ID VARCHAR(10) NOT NULL,
    MARKET_ID VARCHAR(10) NOT NULL,
    PRIMARY KEY (ID, MARKET_ID),
    FOREIGN KEY (MARKET_ID) REFERENCES MARKETPLACE(MARKET_ID),
    FOREIGN KEY (ID) REFERENCES ADMINISTRATIVE(ID)
);

CREATE TABLE ADD_PRODUCTS (
    MARKET_ID VARCHAR(10) NOT NULL,
    SPONSOR_ID VARCHAR(10) NOT NULL,
    PRODUCT_LIST VARCHAR(500) NOT NULL,
    PRIMARY KEY (MARKET_ID, SPONSOR_ID),
    FOREIGN KEY (MARKET_ID) REFERENCES MARKETPLACE(MARKET_ID),
    FOREIGN KEY (SPONSOR_ID) REFERENCES SPONSOR(SPONSOR_ID)
);

CREATE TABLE PRODUCTS (
    MARKET_ID VARCHAR(10) NOT NULL,
    PRODUCTS VARCHAR(50) NOT NULL,
    PRIMARY KEY (MARKET_ID, PRODUCTS),
    FOREIGN KEY (MARKET_ID) REFERENCES MARKETPLACE(MARKET_ID)
);