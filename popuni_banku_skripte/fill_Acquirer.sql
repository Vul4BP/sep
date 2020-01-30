#-------------------------------ACCOUNTS---------------------------------
INSERT INTO `acquirer`.`account` (`id`, `amount`) VALUES ('1', '0');	#PCC

INSERT INTO `acquirer`.`account` (`id`, `amount`) VALUES ('2', '0');	#STEVAN -> PRODAVAC

INSERT INTO `acquirer`.`account` (`id`, `amount`) VALUES ('3', '200');	#MARKO 	-> USPESNO

INSERT INTO `acquirer`.`account` (`id`, `amount`) VALUES ('4', '0');	#JOVAN 	-> NEMA SEREDSTAVA

#-------------------------------CARDS---------------------------------
INSERT INTO `acquirer`.`card` (`id`, `holder_name`, `pan`, `security_code`, `valid_to`, `account_id`) 
VALUES ('1', 'Stevan Vulic', '0000000001', '123', '2020-10-10', '2');

INSERT INTO `acquirer`.`card` (`id`, `holder_name`, `pan`, `security_code`, `valid_to`, `account_id`) 
VALUES ('2', 'Marko Markovic', '0000000002', '123', '2020-10-10', '3');

INSERT INTO `acquirer`.`card` (`id`, `holder_name`, `pan`, `security_code`, `valid_to`, `account_id`) 
VALUES ('3', 'Jovan Jovanovic', '0000000003', '123', '2020-10-10', '4');

#-------------------------------CLIENTS---------------------------------
INSERT INTO `acquirer`.`client` (`id`, `first_name`, `last_name`, `merchant_id`, `merchant_password`, `account_id`) 
VALUES ('1', 'Pcc', 'Pcc', '00000000', '00000000', '1');

INSERT INTO `acquirer`.`client` (`id`, `first_name`, `last_name`, `merchant_id`, `merchant_password`, `account_id`) 
VALUES ('2', 'Stevan', 'Vulic', '12345678', 'aaaaaaaa', '2');

commit;
