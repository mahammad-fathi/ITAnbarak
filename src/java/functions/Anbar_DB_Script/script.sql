CREATE TABLE ANBAR (ITEMNAME VARCHAR(255) NOT NULL, COMMENT CLOB(2147483647), DEPARTEMENT VARCHAR(255), ITEMCODE VARCHAR(255), TARIGH DATE, TEDAD INTEGER, PRIMARY KEY (ITEMNAME))
CREATE TABLE DELIVERY (APPLICANT_DEPARTMENT VARCHAR(255), COMMENT CLOB(2147483647), ITEMCODE VARCHAR(255), TEDAD INTEGER, TYPE SMALLINT DEFAULT 0, USERNAME VARCHAR(255), WO INTEGER, WR INTEGER, ITEMNAME VARCHAR(255) NOT NULL, TARIGH DATE NOT NULL, APPLICANT_NAME VARCHAR(255) NOT NULL, PRIMARY KEY (ITEMNAME, TARIGH, APPLICANT_NAME))
ALTER TABLE DELIVERY ADD CONSTRAINT DELIVERY_ITEMNAME FOREIGN KEY (ITEMNAME) REFERENCES ANBAR (ITEMNAME)
