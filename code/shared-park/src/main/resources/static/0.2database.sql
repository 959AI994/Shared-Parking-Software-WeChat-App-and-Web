--dataBase：shared_park

/*角色表*/
create table role
(
    roleId    int auto_increment comment '角色编号' primary key,
    name      varchar(8)    not null comment '角色名称',
    describes varchar(20)   not null comment '描述',
    state     int default 1 not null comment '角色状态，0：禁用，1：启用'
) comment '角色表';


/*用户表*/
create table users
(
    userId    int auto_increment comment '用户编号'
        primary key,
    name      varchar(8)      not null comment '姓名',
    sex       char(2)         not null comment '性别',
    phone     char(11)        not null comment '手机号',
    password  varchar(12)     not null comment '登录密码',
    openId    varchar(150) comment '微信唯一用户标识',
    salt      varchar(255) comment '盐',
    email     varchar(25) null comment '电子邮件',
    dateBirth char(10)        not null comment '出生年月',
    idCardNo  char(18)        not null comment '身份证号',
    income    float default 0 not null comment '收入',
    roleId    int             not null comment '角色ID',
    state     int   default 1 not null comment '是否有效，0：禁用，1：启用',
    CONSTRAINT sex_cons CHECK (sex in ('男', '女')),
    CONSTRAINT fk_users_roleId FOREIGN KEY (roleId) REFERENCES role (roleId)
) comment '用户表';

/*系统权限*/
create table permission
(
    permissionId int auto_increment comment '系统权限编号' primary key,
    name         varchar(8)    not null comment '权限名称',
    icon         varchar(10) null comment '图标',
    url          varchar(50) null comment '菜单url',
    code         varchar(100)  not null comment '权限编码',
    parentId     bigint(20) comment '父id',
    type         int comment '类型，1：菜单，2：按钮',
    describes    varchar(20)   not null comment '权限描述',
    state        int default 1 not null comment '角色状态，0：禁用，1：启用'
) comment '系统权限';

/*角色权限关系*/
create table role_permission
(
    rolePermissionId int auto_increment comment '角色权限关系编号' primary key,
    roleId           int           not null comment '角色ID',
    permissionId     int           not null comment '权限ID',
    state            int default 1 not null comment '角色状态，0：禁用，1：启用',
    CONSTRAINT fk_rolePermission_roleId FOREIGN KEY (roleId) REFERENCES role (roleId),
    CONSTRAINT fk_rolePermission_permissionId FOREIGN KEY (permissionId) REFERENCES permission (permissionId)
) comment '角色权限关系';


/*--车辆表--*/
create table car
(
    carId     int auto_increment comment '车ID' primary key,
    carNumber char(8)     not null unique comment '车牌号',
    userId    int comment '归属用户',
    brand     varchar(12) not null comment '汽车品牌',
    carType   char(11)    not null comment '汽车类型',
    CONSTRAINT fk_car_userId FOREIGN KEY (userId) REFERENCES users (userId)
) comment '车辆表';

/*--停车场表--*/
create table parking
(
    parkingId              int auto_increment  comment '停车场编号' primary key,
--     parkingName            varchar(30) not null comment '停车场名称',
    address                varchar(12) not null comment '地址',
    liveInGarden           varchar(12) not null comment '居住花园',
    longitude              varchar(20) not null comment '经度',
    latitude               varchar(20) not null comment '纬度',
    totalParkingSpaces     int         not null comment '总车位数',
    temporaryParkingSpaces int         not null comment '临时车位数',
    freeTime               int null comment '免费停车时间',
    rentCar                int         not null comment '月租车位数'

) comment '小区停车场表';


/*--车位归属表--*/
create table parkingSpace
(
    parkingSpaceId  int auto_increment comment '车位归属编号' primary key,
    parkingLotNo varchar (20) not null comment '车位号',
    effectiveDate  datetime      not null comment '有效日期',
    isRent         int default 1 not null comment '是否租用，0：未占用，1：在租用',
    isTakeUp       int default 1 not null comment '是否占用，0：占用，1：未占用',
    parkingId      int comment '停车场编号',
    userId      int comment '用户ID',
--      sharStartTime datetime not null comment '开始结束时间',
    shareEndTime   datetime      not null comment '共享结束时间',
    CONSTRAINT fk_parkingSpace_parkingId FOREIGN KEY (parkingId) REFERENCES parking (parkingId),
    CONSTRAINT fk_parkingSpace_userId FOREIGN KEY (userId) REFERENCES users (userId)

) comment '车位表';


/*收费标准*/
create table chargeStandard
(
    type      varchar(6) comment '停车类型',
    parkingId int comment '停车场编号',
    price     float comment '价钱',
    parkHours int comment '停放小时',
    CONSTRAINT fk_chargeStandard_parkingId FOREIGN KEY (parkingId) REFERENCES parking (parkingId),
    primary key (type, parkingId)

) comment '收费标准';


/*业主缴费*/
create table ownerPayCost
(
    parkingSpaceId int comment '车位号',
    parkingId      int comment '停车场编号',
    type           varchar(6) comment '停车类型',
    paymentTime    datetime comment '缴费时间',
    month          int comment '月份',
    paymentAmount  float comment '支付金额',
    MethodPayment  varchar(6) comment '支付方式',
    CONSTRAINT fk_ownerPayCost_parkingSpace FOREIGN KEY (parkingSpaceId) REFERENCES parkingSpace (parkingSpaceId),
    CONSTRAINT fk_ownerPayCost_parkingId FOREIGN KEY (parkingId) REFERENCES parking (parkingId),
    CONSTRAINT fk_ownerPayCost_type FOREIGN KEY (type) REFERENCES chargeStandard (type),
    primary key (paymentTime, parkingId, parkingSpaceId)
) comment '业主缴费';


/*预约停车*/
create table parking_reservation
(
    parkingReservationId bigint auto_increment comment '预约停车订单号' primary key,
    carId                int comment '车编号',
--     parkingId            int comment '停车场编号',
    parkingSpaceId       int comment '车位编号',
    uId                  int comment '用户ID',
    appointmentTime      datetime comment '预约时间',
    createTime           datetime comment '创建时间',
    state                char(6) comment '进行中，已完成，取消',
    paymentAmount        float comment '支付金额',
    MethodPayment        varchar(6) comment '支付方式',
    approachTime         datetime comment '进场时间',
    playingTime          datetime comment '出场时间',
    intOperatorId        int comment '入口操作员编号',
    outOperatorId        int comment '出口操作员编号',
    CONSTRAINT fk_parkingReservation_carNumber FOREIGN KEY (carId) REFERENCES car (carId),
--     CONSTRAINT fk_parkingReservation_parkingId FOREIGN KEY (parkingId) REFERENCES parking (parkingId),
    CONSTRAINT fk_parkingReservation_uId FOREIGN KEY (uId) REFERENCES users (userId),
    CONSTRAINT fk_parkingReservation_parkingSpaceId FOREIGN KEY (parkingSpaceId) REFERENCES parkingSpace (parkingSpaceId),
    CONSTRAINT fk_parkingReservation_intOperatorId FOREIGN KEY (intOperatorId) REFERENCES users (userId),
    CONSTRAINT fk_parkingReservation_outOperatorId FOREIGN KEY (outOperatorId) REFERENCES users (userId)
) comment '预约停车';


/*普通停车订单*/
create table ordinaryParkOrder
(
    ordinaryParkOrder bigint auto_increment comment '普通停车订单号' primary key,
    carNumber         char(8) comment '车牌号',
    parkingId        int comment '停车场编号',
    intOperatorId     int comment '入口操作员编号',
    outOperatorId     int comment '出口操作员编号',
    type              varchar(6) comment '停车类型',
    approachTime      datetime comment '进场时间',
    playingTime       datetime comment '出场时间',
    state             char(6) comment '状态',
    paymentAmount     float comment '支付金额',
    MethodPayment     varchar(6) comment '支付方式',
    CONSTRAINT fk_stopOrder_parkingId FOREIGN KEY (parkingId) REFERENCES parking (parkingId),
    CONSTRAINT fk_stopOrder_intOperatorId FOREIGN KEY (intOperatorId) REFERENCES users (userId),
    CONSTRAINT fk_stopOrder_outOperatorId FOREIGN KEY (outOperatorId) REFERENCES users (userId)
) comment '普通停车';

