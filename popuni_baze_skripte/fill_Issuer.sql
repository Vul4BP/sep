#-------------------------------ACCOUNTS---------------------------------
INSERT INTO `issuer`.`account` (`id`, `amount`) VALUES ('1', '0');	#PCC

INSERT INTO `issuer`.`account` (`id`, `amount`) VALUES ('2', '200');	#PETAR 	-> USPESNO

INSERT INTO `issuer`.`account` (`id`, `amount`) VALUES ('3', '0');	#MILOS 	-> NEMA SEREDSTAVA

#-------------------------------CARDS---------------------------------
INSERT INTO `issuer`.`card` (`id`, `holder_name`, `pan`, `security_code`, `valid_to`, `account_id`) 
VALUES ('1', 'Petar Petrovic', '0100000001', '123', '2020-10-10', '2');

INSERT INTO `issuer`.`card` (`id`, `holder_name`, `pan`, `security_code`, `valid_to`, `account_id`) 
VALUES ('2', 'Milos Milosevic', '0100000002', '123', '2020-10-10', '3');

#-------------------------------CLIENTS---------------------------------
INSERT INTO `issuer`.`client` (`id`, `first_name`, `last_name`, `payer_id`, `payer_password`, `account_id`) 
VALUES ('1', 'Pcc', 'Pcc', '00000000', '00000000', '1');

commit;
