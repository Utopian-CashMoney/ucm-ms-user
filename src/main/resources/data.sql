--Accounts
INSERT INTO `account` VALUES ( 1, 'Primary Checking', 'DEBIT', true, 100, 1 );
INSERT INTO `account` VALUES ( 2, 'Primary Savings', 'DEBIT', false, 0, 0 );
INSERT INTO `account` VALUES ( 3, 'Primary Credit', 'CREDIT', true, 500, 1 );

----Users
INSERT INTO `users` VALUES ( 1, 'john1', 'john@gmail.com', 'Aa1?zzzz', 1222343322, 'john', 'aa', '1287 S Hampton Ave', 'Clarion', 'LA', '33403', 1);
INSERT INTO `users` VALUES ( 2, 'smith2', 'smith@gmail.com', 'Aa1?zzzzz', 1221345322, 'smith', 'bb', '6523 N Kornway Rd', 'Ransing', 'MI', '78932', 1);
INSERT INTO `users` VALUES ( 3, 'Doe', 'doe@gmail.com', 'Aa1?zzzzzz', 1621345322, 'doe', 'cc', '8870 W Sandy Ave', 'WestLake', 'CA', '54709', 1);

----Confirm Token
--INSERT INTO `confirm_token` VALUES ( 1, 1, 'vdfvdvjsbbcsbdcjbsahvbs13hbhvj3', '2020-01-01 10:10:10+05:30');


--UserAccounts
INSERT INTO `user_account` VALUES ('CHKACCTNMBR', 1, 1, 125.32);
INSERT INTO `user_account` VALUES ('SVNGACCTNMBR', 1, 2, 753.25);
INSERT INTO `user_account` VALUES ('CRDTACCTNMBR', 1, 3, -25.36);

--Cards
INSERT INTO `card` VALUES ( 1, 'CHKACCTNMBR', 'CHKCARDNMBR',  CURDATE(), '333' );
INSERT INTO `card` VALUES ( 2, 'CRDTACCTNMBR', 'CRDTCARDNMBR', CURDATE(), '666' );

--Transactions
INSERT INTO `transaction` VALUES (1, 'CHKACCTNMBR', 100, CURRENT_TIMESTAMP(), 'SOMEONE GIV U $', 1, 0);
INSERT INTO `transaction` VALUES (2, 'CHKACCTNMBR', -100, CURRENT_TIMESTAMP(), 'SOMEONE TAK UR $', 0, 0);