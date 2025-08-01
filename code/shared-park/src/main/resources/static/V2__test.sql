-- 修改车位表主键
alter table parkingspace drop primary key;
-- ALTER  TABLE 表名 MODIFY COLUMN 字段名 新数据类型 新类型长度  新默认值  新注释; -- COLUMN可以省略
-- alter  table parkingspace modify  column parkingspaceId  varchar(30) COMMENT '车位号'; -- 正常，能修改字段类型、类型长度、默认值、注释
alter table parkingspace modify column parkingspaceId varchar(30);
alter table parkingspace add primary key(parkingspaceId,parkingId);

ALTER TABLE parking_reservation DROP FOREIGN KEY  fk_parkingReservation_parkingSpaceId;
ALTER TABLE `parking_reservation` ADD CONSTRAINT fk_parkingReservation_parkingSpaceId FOREIGN KEY (parkingSpaceId) REFERENCES parkingSpace parkingSpace (parkingSpaceId,parkingId);





INSERT INTO `shared_park`.`parking`(`parkingId`, `address`, `liveInGarden`, `longitude`, `latitude`,
                                    `totalParkingSpaces`, `temporaryParkingSpaces`, `freeTime`, `rentCar`)
VALUES ('1', '玉林师范学院', '中鼎花园', '161615651', '12615155', 20, 10, 56, 88);

INSERT INTO `shared_park`.`parking`(`parkingId`, `address`, `liveInGarden`, `longitude`, `latitude`,
                                    `totalParkingSpaces`, `temporaryParkingSpaces`, `freeTime`, `rentCar`)
VALUES ('2', '完蛋', '完蛋花园33', '25662', '5655444', 202, 66, 44, 99);

INSERT INTO `shared_park`.`parking`(`parkingId`, `address`, `liveInGarden`, `longitude`, `latitude`,
                                    `totalParkingSpaces`, `temporaryParkingSpaces`, `freeTime`, `rentCar`)
VALUES ('3', '碧桂园', '万达花园33', '25662', '5655444', 202, 66, 44, 99);



INSERT INTO `shared_park`.`car`(`carNumber`, `userId`, `brand`, `carType`) VALUES ('桂K 5527G', 2, '广汽本田', '小车');
INSERT INTO `shared_park`.`car`(`carNumber`, `userId`, `brand`, `carType`) VALUES ('桂K 11924', 3, 'BYD', '小车');
INSERT INTO `shared_park`.`car`(`carNumber`, `userId`, `brand`, `carType`) VALUES ('桂K 66666', 1, '奥迪', '小车');
