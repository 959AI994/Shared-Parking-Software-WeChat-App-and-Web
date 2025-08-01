INSERT INTO `shared_park`.`parking`( `address`, `liveInGarden`, `longitude`, `latitude`,
                                    `totalParkingSpaces`, `temporaryParkingSpaces`, `freeTime`, `rentCar`)
VALUES ('玉林师范学院', '中鼎花园', '161615651', '12615155', 20, 10, 56, 88);

INSERT INTO `shared_park`.`parking`( `address`, `liveInGarden`, `longitude`, `latitude`,
                                    `totalParkingSpaces`, `temporaryParkingSpaces`, `freeTime`, `rentCar`)
VALUES ('完蛋', '完蛋花园33', '25662', '5655444', 202, 66, 44, 99);

INSERT INTO `shared_park`.`parking`(`address`, `liveInGarden`, `longitude`, `latitude`,
                                    `totalParkingSpaces`, `temporaryParkingSpaces`, `freeTime`, `rentCar`)
VALUES ('碧桂园', '万达花园33', '25662', '5655444', 202, 66, 44, 99);



INSERT INTO `shared_park`.`car`(`carNumber`, `userId`, `brand`, `carType`) VALUES ('桂K 5527G', 2, '广汽本田', '小车');
INSERT INTO `shared_park`.`car`(`carNumber`, `userId`, `brand`, `carType`) VALUES ('桂K 11924', 3, 'BYD', '小车');
INSERT INTO `shared_park`.`car`(`carNumber`, `userId`, `brand`, `carType`) VALUES ('桂K 66666', 1, '奥迪', '小车');

INSERT INTO `shared_park`.`parkingspace`(`parkingLotNo`, `effectiveDate`, `parkingId`, `shareEndTime`) VALUES ('A1', '2021-09-26 10:45:35', 1, '2021-09-26 10:45:41');
INSERT INTO `shared_park`.`parkingspace`(`parkingLotNo`, `effectiveDate`, `parkingId`, `shareEndTime`) VALUES ('A2', '2021-09-26 10:45:35', 1, '2021-09-26 10:45:41');
INSERT INTO `shared_park`.`parkingspace`(`parkingLotNo`, `effectiveDate`, `parkingId`, `shareEndTime`) VALUES ('A3', '2021-09-26 10:45:35', 2, '2021-09-26 10:45:41');