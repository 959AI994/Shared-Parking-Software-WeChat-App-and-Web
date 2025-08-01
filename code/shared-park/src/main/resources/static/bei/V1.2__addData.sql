INSERT INTO `shared_park`.`role`(`roleId`, `name`, `describes`) VALUES (1, 'admin', '管理员');
INSERT INTO `shared_park`.`role`(`roleId`, `name`, `describes`) VALUES (2, 'user', '普通用户');
INSERT INTO `shared_park`.`role`(`roleId`, `name`, `describes`) VALUES (3, 'owner', '业主');
INSERT INTO `shared_park`.`role`(`roleId`, `name`, `describes`) VALUES (4, 'operator', '操作员');


INSERT INTO `shared_park`.`users`(`userId`, `name`, `sex`, `phone`, `password`, `salt`, `email`, `dateBirth`, `idCardNo`, `roleId`) VALUES (1, 'admin', '男', '18377522951', '123456', 'admin', '2305975309@qq.com', '1999-01-26', 'system', 1);
INSERT INTO `shared_park`.`users`(`userId`, `name`, `sex`, `phone`, `password`, `salt`, `email`, `dateBirth`, `idCardNo`, `roleId`) VALUES (2, 'lbk', '男', '18377522952', '123456', 'abc', '3305975309@qq.com', '1996-01-26', 'user', 2);
INSERT INTO `shared_park`.`users`(`userId`, `name`, `sex`, `phone`, `password`, `salt`, `email`, `dateBirth`, `idCardNo`, `roleId`) VALUES (3, 'kkk', '男', '14377522952', '123456', 'abc', '4305975309@qq.com', '1998-01-30', 'user', 2);


INSERT INTO `shared_park`.`permission`(`permissionId`, `name`, `icon`, `code`, `type`, `describes`) VALUES (1, 'vip', 'uu', 'sys:vip', 1, 'vip');
INSERT INTO `shared_park`.`permission`(`permissionId`, `name`, `icon`, `code`, `type`, `describes`) VALUES (2, '添加用户', 'add', 'sys:addUser', 2, '添加用户');
INSERT INTO `shared_park`.`permission`(`permissionId`, `name`, `icon`, `code`, `type`, `describes`) VALUES (3, '修改用户', 'update', 'sys:updateUser', 3, '修改用户');


INSERT INTO `shared_park`.`role_permission`(`rolePermissionId`, `roleId`, `permissionId`) VALUES (1, 1, 1);
INSERT INTO `shared_park`.`role_permission`(`rolePermissionId`, `roleId`, `permissionId`) VALUES (2, 1, 2);
INSERT INTO `shared_park`.`role_permission`(`rolePermissionId`, `roleId`, `permissionId`) VALUES (3, 2, 1);
INSERT INTO `shared_park`.`role_permission`(`rolePermissionId`, `roleId`, `permissionId`) VALUES (4, 3, 3);