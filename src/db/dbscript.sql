CREATE DATABASE PREGNANCY_CLINICAL_CENTRE;

USE PREGNANCY_CLINICAL_CENTRE;

DROP TABLE IF EXISTS `PregMother`;
CREATE TABLE `PregMother` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `nic` varchar(10) NOT NULL,
  `contactNo` VARCHAR(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `Item` (
  `code` varchar(10) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `unitPrice` decimal(6,2) DEFAULT NULL,
  `qtyOnHand` int DEFAULT NULL,
  PRIMARY KEY (`code`)
);


CREATE TABLE `Order` (
  `id` varchar(100) NOT NULL,
  `date` date DEFAULT NULL,
  `momId` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `momId` (`momId`),
  CONSTRAINT FOREIGN KEY (`momId`) REFERENCES `PregMother` (`id`)
);


CREATE TABLE `OrderDetail` (
  `orderId` varchar(100) NOT NULL,
  `itemCode` varchar(10) NOT NULL,
  `qty` int DEFAULT NULL,
  `unitPrice` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`orderId`,`itemCode`),
  KEY `itemCode` (`itemCode`),
  CONSTRAINT FOREIGN KEY (`orderId`) REFERENCES `Order` (`id`),
  CONSTRAINT FOREIGN KEY (`itemCode`) REFERENCES `Item` (`code`)
);


CREATE TABLE `AdminInformation` (
  `code` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `nic` varchar(10) NOT NULL,
  `personalPhisicion` varchar(100) NOT NULL,
  `emergencyContactNo` VARCHAR(11) NOT NULL,
  `insuaranceCentre` varchar(100) NOT NULL,
  `insuaranceCentreContactNo` VARCHAR(11) NOT NULL,
  `nearestHospital` varchar(100) NOT NULL,
  PRIMARY KEY (`code`)
);


CREATE TABLE `Doctor` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `nic` varchar(10) NOT NULL,
  `contactNo` VARCHAR(11) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `specialArea` varchar(100) NOT NULL,
  `hospital` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `Treatment` (
  `code` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `docName` varchar(100) NOT NULL,
  `patientName` varchar(100) NOT NULL,
  `patContactNo` VARCHAR(11) NOT NULL,
  `cost` decimal(6,2) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `docId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `docId` (`docId`),
  CONSTRAINT FOREIGN KEY (`docId`) REFERENCES `Doctor` (`id`)
);


CREATE TABLE `clinicCard` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `age` varchar(10) NOT NULL,
  `address` varchar(200) NOT NULL,
  `nic` varchar(10) NOT NULL,
  `contactNo` VARCHAR(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `husbandName` varchar(100) NOT NULL,
  `husbandContactNo` VARCHAR(11) NOT NULL,
  `bloodGroup` varchar(10) NOT NULL,
  `weight` varchar(10) NOT NULL,
  `height` varchar(10) NOT NULL,
  `situation` varchar(20) NOT NULL,
  `delMethod` varchar(20) NOT NULL,
  `personalPhisicion` varchar(100) NOT NULL,
  `nearestHospital` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `midWife` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `nic` varchar(10) NOT NULL,
  `contactNo` VARCHAR(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `ptAmount` int NOT NULL,
  `division` varchar(100) NOT NULL,
  `hospital` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `labTest` (
  `code` varchar(100) NOT NULL,
  `description` varchar(200) NOT NULL,
  `patName` varchar(100) NOT NULL,
  `patContactNo` varchar(11) NOT NULL,
  `testDate` date DEFAULT NULL,
  `testCost` decimal(6,2) DEFAULT NULL,
  `docName` varchar(100) NOT NULL,
  `labortaryTester` varchar(100) NOT NULL,
 `labContactNo` varchar(11) NOT NULL,
  `midId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `midId` (`midId`),
  CONSTRAINT FOREIGN KEY (`midId`) REFERENCES `midWife` (`id`)
);