INSERT INTO ACCOUNT (account_number) VALUES ('A123456789');
INSERT INTO OPERATION (label, date, amount, account_number) VALUES ('First deposit', TO_DATE('01/10/2023', 'dd/MM/yyyy'), 1000, 'A123456789');
INSERT INTO OPERATION (label, date, amount, account_number) VALUES ('Cash deposit', TO_DATE('03/10/2023', 'dd/MM/yyyy'), 120, 'A123456789');
INSERT INTO OPERATION (label, date, amount, account_number) VALUES ('Withdrawal', TO_DATE('12/10/2023', 'dd/MM/yyyy'), -20, 'A123456789');