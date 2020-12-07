USE `polyclinics`;

INSERT INTO `medical_units`(name, address) 
VALUES
('Sanitas Cluj-Napoca', 'str. Observatorului, nr. 314'),
('Sanitas Arad', 'str. Aurel Vlaicu, nr. 91'),
('Sanitas Iasi', 'str. Mihai Eminescu, nr. 1'),
('Sanitas Craiova', 'str. Calea Bucuresti, nr. 1'),
('Sanitas Costanta', 'str. Vaselor, nr. 24');

INSERT INTO `medical_unit_schedule`(idMedicalUnit, dayOfWeek, startHour, endHour) 
VALUES
('1', 'Luni', '08:00', '20:00'),
('1', 'Marti', '08:00', '20:00'),
('1', 'Miercuri', '08:00', '20:00'),
('1', 'Joi', '08:00', '20:00'),
('1', 'Vineri', '08:00', '18:00'),
('1', 'Sambata', '08:00', '16:00'),

('2', 'Luni', '08:30', '20:00'),
('2', 'Marti', '08:30', '20:00'),
('2', 'Miercuri', '08:30', '20:00'),
('2', 'Joi', '08:30', '20:00'),
('2', 'Vineri', '08:30', '18:00'),
('2', 'Sambata', '08:30', '16:00'),
('2', 'Duminica', '08:30', '12:00'),

('3', 'Luni', '07:30', '19:00'),
('3', 'Marti', '07:30', '19:00'),
('3', 'Miercuri', '07:30', '19:00'),
('3', 'Joi', '07:30', '19:00'),
('3', 'Vineri', '07:30', '17:00'),
('3', 'Sambata', '07:30', '12:00'),

('4', 'Luni', '07:30', '20:00'),
('4', 'Marti', '07:30', '20:00'),
('4', 'Miercuri', '07:30', '20:00'),
('4', 'Joi', '07:30', '20:00'),
('4', 'Vineri', '07:30', '18:00'),
('4', 'Sambata', '07:30', '15:00'),

('5', 'Luni', '07:45', '20:00'),
('5', 'Marti', '07:45', '20:00'),
('5', 'Miercuri', '07:45', '20:00'),
('5', 'Joi', '07:45', '20:00'),
('5', 'Vineri', '07:45', '18:00'),
('5', 'Sambata', '07:45', '15:00'),
('5', 'Duminica', '08:00', '12:00');

INSERT INTO `cabinets`(idMedicalUnit, name) 
VALUES
('1', 'Cardiologie'),
('1', 'Dermatologie'),
('1', 'Chirurgie plastica'),
('1', 'Endocrinologie'),
('1', 'Stomatologie'),
('1', 'ORL'),
('1', 'Ortopedie'),
('1', 'Endoscopie'),
('1', 'Radiologie'),
('1', 'RMN'),
('1', 'Sala de operatie 1'),
('1', 'Sala de operatie 2'),

('2', 'Cardiologie'),
('2', 'Dermatologie'),
('2', 'Stomatologie'),
('2', 'Endocrinologie'),
('2', 'Radiologie'),
('2', 'Endoscopie'),

('3', 'Cardiologie'),
('3', 'Dermatologie'),
('3', 'Chirurgie plastica'),
('3', 'Endocrinologie'),
('3', 'Stomatologie'),
('3', 'ORL'),
('3', 'Ortopedie'),
('3', 'Endoscopie'),
('2', 'Radiologie'),
('3', 'RMN'),
('3', 'Sala de operatie'),

('4', 'Cardiologie'),
('4', 'Dermatologie'),
('4', 'Endocrinologie'),
('4', 'Stomatologie'),
('4', 'ORL'),
('4', 'Ortopedie'),
('4', 'Endoscopie'),
('4', 'Radiologie'),

('5', 'Cardiologie'),
('5', 'Dermatologie'),
('5', 'Endocrinologie'),
('5', 'Stomatologie'),
('5', 'ORL'),
('5', 'Ortopedie'),
('5', 'Endoscopie'),
('5', 'Radiologie'),
('5', 'Sala de operatie');

INSERT INTO `equipments`(name) 
VALUES
('Computer tomograf'),
('RMN'),
('EKG'),
('Ecocardiograf'),
('Aparat Holter'),
('Dermatoscop Digital'),
('Lampa UV'),
('Ecograf endocrinologie'),
('Scaun stomatologic'),
('Unitate de diagnosticare ORL'),
('Camera endoscopica ORL'),
('Ecograf'), 
('Sistem chirurgical'),
('Laparoscop'),
('Instalatie radiologica');

-- DELETE FROM `equipments`;

INSERT INTO `cabinet_equipments`(idCabinet, idEquipment) 
VALUES
('1', '3'),
('1', '4'),
('1', '5'),
('2', '6'),
('2', '7'),
('3', '13'),
('4', '8'),
('5', '9'),
('6', '10'),
('6', '11'),
('8', '12'),
('9', '15'),
('10', '1'),
('10', '2'),
('11', '13'),
('11', '14'),
('12', '13'),
('12', '14'),

('13', '3'),
('13', '4'),
('13', '5'),
('14', '6'),
('14', '7'),
('15', '9'),
('16', '8'),
('17', '15'),
('18', '12'),

('19', '3'),
('19', '4'),
('19', '5'),
('20', '6'),
('20', '7'),
('21', '13'),
('22', '8'),
('23', '9'),
('24', '10'),
('24', '11'),
('26', '12'),
('27', '15'),
('28', '1'),
('28', '2'),
('29', '13'),
('29', '14'),

('30', '3'),
('30', '4'),
('30', '5'),
('31', '6'),
('31', '7'),
('32', '8'),
('33', '9'),
('34', '10'),
('34', '11'),
('36', '12'),
('37', '15'),

('38', '3'),
('38', '4'),
('38', '5'),
('39', '6'),
('39', '7'),
('40', '8'),
('41', '9'),
('42', '10'),
('42', '11'),
('44', '12'),
('45', '15'),
('46', '13'),
('46', '14');

INSERT INTO `accreditations`(name) 
VALUES
('Ecografie'),
('Chirurgie'),
('Radiologie'),
('Explorare computer tomograf'),
('Imagistica prin rezonanta magnetica');

INSERT INTO `specialities`(name) 
VALUES
('Cardiologie'),
('Dermatologie si venerologie'),
('Chirurgie'),
('Chirurgie plastica'),
('Radiologie si imagistica'),
('Stomatologie'),
('ORL'),
('Ortopedie'),
('Pediatrie'),
('Oftalmologie'),
('Endocrinologie');

INSERT INTO `roles`(name) 
VALUES
('default'),
('receptioner'),
('resurse_umane'),
('asistent_medical'),
('medic'),
('contabil'),
('administrator'),
('super_administrator');

INSERT INTO `role_permissions`(idRole, permission) 
VALUES
('2', 'hr.read'),
('2', 'fc.read'),
('2', 'oa.appointment.read'),
('2', 'oa.appointment.write'),
('2', 'oa.patients_register.read'),
('2', 'oa.patients_register.write'),
('2', 'oa.receipt.write'),
('3', 'hr.read'),
('3', 'hr.read.other'),
('3', 'hr.write.other'),
('3', 'fc.read'),
('4', 'hr.read'),
('4', 'fc.read'),
('4', 'oa.medical_checkup.write'),
('4', 'oa.medical_checkup.read'),
('5', 'hr.read'),
('5', 'fc.read'),
('5', 'oa.history.write'),
('5', 'oa.history.read'),
('5', 'oa.medical_raport.write'),
('5', 'oa.medical_raport.read'),
('6', 'hr.read'),
('6', 'fc.read'),
('6', 'fc.write'),
('6', 'fc.read.other'),
('6', 'fc.write.other'),
('7', '*'),
('8', '*'),
('8', '+');

INSERT INTO `patients`(cnp, lastName, firstName) 
VALUES
('2910815468725', 'Alexa', 'Magda'),
('2750329315938', 'Ambrus', 'Erika'),
('1801119378613', 'Aproslavesei', 'Ion Dumitru'),
('1201204085247', 'Iliescu', 'Ion'),
('2380127165640', 'Georgescu', 'Carmen'),
('2460512257129', 'Marin', 'Florina'),
('1471020095263', 'Gras', 'Dumitru'),
('1520619148967', 'Tomoiaga', 'Vali'),
('2920430215231', 'Manea', 'Ana Georgiana'),
('1960408068054', 'Vijelie', 'Ioan Andrei'),
('1971005087985', 'Haralamb-Vantu', 'Codrin Mihai'),
('2961028088875', 'Schnitzel', 'Ana Maria'),
('5000604169904', 'Popescu', 'Daniel'),
('6000930329136', 'Stoica', 'Dana Andreea'),
('6000822065974', 'Halas', 'Elisabeta'),
('2991208015482', 'Boldea', 'Crina'),
('2971003128722', 'Fekete', 'Orsoyla');

INSERT INTO `employees` (`cnp`,`lastName`,`firstName`,`address`,`phoneNum`,`email`,`iban`,`contractNum`,`employmentDate`,`position`,`salary`,`workedHrs`) 
VALUES
	('2901204019549','Ionescu','Oana','Cluj-Napoca, str. Lacul Rosu nr. 13','0745634674','ionescu_oana@gmail.com','RO84PORL3823333897221298','1','2018-06-10','Medic',15000,120),
	('2701204068831','Georgescu','Ileana','Cluj-Napoca, str. Lacul Rosu nr. 13','0745634472','georgescu.ileana@yahoo.com','RO02PORL1553658265733295','2','2000-07-20','Medic',20000,130),
	('1701204067125','Varga','Robert','Cluj-Napoca, str. Avram Iancu','0723623723','robert.varga@gmail.com','RO14PORL3819764854464569','3','2007-01-23','Asistent Medical',5000,120),
	('2701203534721','Rosca','Dana','Cluj-Napoca, str. Avram Iancu nr. 2','0723625378','dana9734@yahoo.com','RO40PORL5463823379519984','4','1999-01-04','Receptioner',3000,120),
	('2701204066352','Leu','Nicoleta','Cluj-Napoca, str. Petru Maior 29-19','0762523457','leu.nicoleta422@gmail.com','RO96PORL9649944214969714','5','2003-01-30','Contabil',3600,120),
	('1701204469135','Bulgarescu','Matei','Floresti, Calea Floresti','0745237262','bulg_matei@gmail.com','RO97PORL7474715959592757','6','1980-08-30','HR',3601,120),
	('2901204012456','Ionescu','Ioana','Arad, str. Lacul Rosu nr. 13','0745632367','ionescu_ioana34@gmail.com','RO24PORL3823333897221296','7','2014-06-10','Medic',15000,120),
	('2701204737245','Georgescu','Oana','Arad, str. Lacul Rosu nr. 13','0745639474','georgescu_oana3@cs.utcn.ro','RO63PORL1553658265733295','8','2000-01-20','Medic',17000,120),
	('1701201757453','Florescu','Raul','Arad, str. Avram Iancu','0723629536','florescuraul10@cs.utcn.ro','RO74PORL3819764854464564','9','2009-01-23','Asistent Medical',5000,120),
	('2701209623568','Rosu','Daniela','Arad, str. Avram Iancu nr. 2','0723625834','danielarosu@yahoo.com','RO84PORL5463823379519983','10','1991-01-04','Receptioner',3000,120),
	('2701867345247','Crin','Izabela','Arad, str. Petru Maior 29-19','0762523572','crin_iza_1024@gmail.com','RO11PORL9649944214969712','11','2013-01-30','Contabil',3600,120),
	('1701223675345','Grigorescu','Matei','Arad, Calea Floresti','0745234145','grigore.matei124@gmail.com','RO12PORL7474515959592751','12','1976-08-30','HR',3601,120),
	('2801204117404','Baran','Maria Ileana','Iasi, str. Nicolae Iorga nr. 13','0753723634','baran_maria_ileana@gmail.com','RO61PORL2985652863833938','13','2011-07-10','Medic',15000,120),
	('2931204115436','Bitoleanu','Alexandra Ioana','Iasi, str. Lacul Rosu','0745537474','bitoleanu_ale2@yahoo.com','RO35RZBR8945177663379578','14','2019-12-20','Medic',17000,120),
	('1961204466262','Darie','Emanuel','Iasi, str. Mihai Viteazu nr. 2','0723626386','darie.emanuel5@gmail.com','RO83RZBR6275958249175962','15','2001-09-23','Asistent Medical',5000,120),
	('2761204115614','David','Denisa Elena','Iasi, str. Avram Iancu nr. 4','0714724834','denisae_david@yahoo.com','RO85PORL4861432928789566','16','1995-01-04','Receptioner',4000,120),
	('2851204119863','Popa','Izabela Ioana','Iasi, str. Ion Luca Caragiale nr. 16','0786456872','popa_ioana_elena@gmail.com','RO20PORL2744493865283367','17','2007-03-30','Contabil',3400,120),
	('1911204048836','Munteanu','Victor Mihai','Iasi, Calea Bucuresti nr. 2','0745936743','munteanu.victor@gmail.com','RO09PORL8289969499888436','18','1985-08-30','HR',3700,120),
	('2801204117404','Giurgiu','Cristina','Iasi, Craiova, str. Avram Iancu nr. 13','0784573674','giurgiu_cristina@gmail.com','RO14PORL4288784224545331','19','2011-02-06','Medic',12400,120),
	('2931204115436','Florea','Ionela Mihaela','Craiova, str. Mihai Viteazu nr. 1','0774856846','florea_ionela@yahoo.com','RO89RZBR5887486576599979','20','1998-11-12','Medic',11150,120),
	('1961204466262','Lascu','Vlad Ionut','Craiova, str. Mihai Viteazu nr. 2','0784674859','lascu.ionut7@gmail.com','RO07RZBR2772478373358274','21','2013-07-01','Asistent Medical',5000,120),
	('2761204115614','Helerea','Ramona Andreea','Craiova, str. Avram Iancu nr. 4','0702375386','helerea_ramona24@yahoo.com','RO60RZBR4193362912123856','22','1995-04-23','Receptioner',3800,120),
	('2851204119863','Macovei','Larisa','Craiova, str. Ion Luca Caragiale nr. 3','0728574930','macovei_larisa45@gmail.com','RO22RZBR6312293294421645','23','2002-07-25','Contabil',3400,120),
	('1911204048836','Zamfira','Gabriel Alexandru','Craiova, Calea Bucuresti','0758275948','gabi_alexandru12@gmail.com','RO27RZBR4923277449898256','24','1974-05-15','HR',3700,120);

INSERT INTO ``
("Leucocite",3.50,10.500),
("Trombocite",150,450),
("Glicemia",70,110),
("Trigliceride",50,150),
("Colesterol",0,200),
("Fibrinogen",200,400),
("Sodiu",135,145),
("Calciu",8.5,10.5),
("Magneziu",1.6,2.5),
("Calciu ionic seric",3.6,5.2),
("Hemoglobina glicozilata",4,5.9),
("Lipide",500,800),
("VLDL",2,38),
("Imunoglobulina A",90,450);