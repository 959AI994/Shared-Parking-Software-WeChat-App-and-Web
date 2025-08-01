
/*--普通用户--*/
create table normalUser
(
    openId             Varchar(20) comment '微信用户唯一标识' primary key,
    nickName                varchar(12) not null comment '昵称',
    gender           char(2) not null comment '性别',
    email              Varchar (18)  comment '邮箱',
    phone               char(11) not null comment '电话',
    carNumber              Varchar (8) not null comment '车牌号'
) comment '普通用户表';


/*--物业后台管理员--*/
create table propertyAdmin
(
    propertyAdminId             int auto_increment comment '管理员编号' primary key,
    userName               varchar(11) not null comment '用户名',
    `passWord`          Varchar(12) not null comment '密码',
    gender              Char(2)  comment '性别',
    phone               char(11) not null comment '电话',
    salt             Varchar(30) not null comment '加密盐'
) comment '物业后台管理员';





/*--业主信息表--*/
create table ownerAuth
(
    ownerAuthId              int auto_increment  comment '业主编号' primary key,
    openId                 Varchar(20) not null comment '关联用户',
    ownerNmae              Varchar(14) not null comment '业主名称',
    gender           char(2) not null comment '性别',
    phone              char(11) not null comment '电话',
    income               float not null comment '收入',
    IdNumber            Varchar (18)  comment '身份证号',
    CONSTRAINT fk_ownerAuth_openId FOREIGN KEY (openId) REFERENCES normalUser(openId)
) comment '业主信息表';



/*--小区停车场表--*/
create table ParkingInfo
(
    parkingInfoId          int auto_increment  comment '停车场编号' primary key,
    liveInGarden           varchar(18) not null comment '居住花园',
    address           varchar(16) not null comment '地址',
    longitude              varchar(20) not null comment '经度',
    latitude               varchar(20) not null comment '纬度',
    freeTime               int null comment '临停免费时间',
    temporarySum int         not null comment '临时车位数'

) comment '小区停车场表';




/*--车位归属表--*/
create table parkingSpace
(
    parkingSpaceId varchar (20) not null comment '车位号',
    parkingInfoId      int comment '停车场编号',
    ownerAuthId      int comment '归属业主',
    effectiveDate  datetime      not null comment '有效日期',
    isRent         int default 1 not null comment '是否租用，0：未租用，1：在租用',
    isTakeUp       int default 1 not null comment '是否占用，0：占用，1：未占用',
    shareStartTime   datetime      not null comment '开始共享时间',
    shareEndTime   datetime      not null comment '结束共享时间',
    CONSTRAINT fk_parkingSpace_ownerAuthId FOREIGN KEY (ownerAuthId) REFERENCES ownerAuth (ownerAuthId),
    CONSTRAINT fk_parkingSpace_parkingInfoId FOREIGN KEY (parkingInfoId) REFERENCES parkingInfo (parkingInfoId),
    primary key (parkingSpaceId,parkingInfoId)
) comment '车位归属表';



/*--操作员--*/
create table operator
(
    operatorId             char(8)  comment '操作员编号',
    parkingInfoId         int  comment '停车场编号',
    operatorName          Varchar(12) not null comment '操作员名字',
    `passWord`            Char(2)  comment '性别',
    gender               char(2) not null comment '性别',
    phone               char(11) not null comment '电话',
    idNumber            Char(18) not null comment '身份证号',
    CONSTRAINT fk_operator_parkingInfoId FOREIGN KEY (parkingInfoId) REFERENCES parkingInfo(parkingInfoId),
    primary key(operatorId,parkingInfoId)
) comment '操作员';



/*收费标准*/
create table chargeStandard
(
    type      varchar(8) comment '停车类型',
    parkingInfoId int comment '停车场编号',
    price     float comment '价钱',
    parkHours int comment '停放小时',
    CONSTRAINT fk_chargeStandard_parkingId FOREIGN KEY (parkingInfoId) REFERENCES parkingInfo (parkingInfoId),
    primary key (type, parkingInfoId)

) comment '收费标准';



/*业主缴费*/
create table ownerPayCost
(
    ownerPayCostId  int auto_increment comment '缴费单编号' primary key,
    parkingSpaceId varchar (20) comment '车位号',
    parkingInfoId      int comment '停车场编号',
    type           varchar(6) comment '停车类型',
    paymentTime    datetime comment '缴费时间',
    `month`          int comment '月份',
    paymentAmount  float comment '支付金额',
    MethodPayment  varchar(6) comment '支付方式',
    CONSTRAINT fk_ownerPayCost_type FOREIGN KEY (type) REFERENCES chargeStandard (type),
    CONSTRAINT fk_ownerPayCost_parkingSpaceId FOREIGN KEY (parkingSpaceId) REFERENCES parkingSpace (parkingSpaceId),
    CONSTRAINT fk_ownerPayCost_parkingInfoId FOREIGN KEY (parkingInfoId) REFERENCES parkingInfo (parkingInfoId)

) comment '业主缴费';


/*预约停车*/
create table parking_reservation
(
    orderId bigint auto_increment comment '预约停车订单号' primary key,
    openId    Varchar(20) comment '微信用户唯一标识',
    parkingSpaceId        Varchar(20) comment '车位号',
    parkingInfoId       int comment '小区停车场编号',
    createTime           datetime comment '创建时间',
    state                char(6) comment '进行中，已完成，取消',
    paymentAmount        float comment '支付金额',
    MethodPayment        varchar(6) comment '支付方式',
    approachTime         datetime comment '进场时间',
    playingTime          datetime comment '出场时间',
    CONSTRAINT fk_parkingReservation_openId FOREIGN KEY (openId) REFERENCES normalUser (openId),
    CONSTRAINT fk_parkingReservation_parkingSpaceId FOREIGN KEY (parkingSpaceId) REFERENCES parkingSpace (parkingSpaceId),
    CONSTRAINT fk_parkingReservation_parkingInfoId FOREIGN KEY (parkingInfoId) REFERENCES parkingInfo (parkingInfoId)
) comment '预约停车';



/*出入记录*/
create table accessRecords
(
    carNumber char(8) comment '车牌号',
    parkingInfoId    int comment '停车场编号',
    intOperatorId      Char(8) comment '入口操作员编号',
    outOperatorId      Char(8) comment '出口操作员编号',
    orderId           bigint comment '预约停车订单',
    state                char(6) comment '进行中，已完成，取消',
    type       char(6) comment '停车类型',
    approachTime         datetime comment '进场时间',
    playingTime          datetime comment '出场时间',
    paymentAmount    float comment '支付金额',
    MethodPayment        varchar(6) comment '支付方式',
    CONSTRAINT fk_accessRecords_parkingInfoId FOREIGN KEY (parkingInfoId) REFERENCES parkingInfo (parkingInfoId),
    CONSTRAINT fk_accessRecords_intOperatorId FOREIGN KEY (intOperatorId) REFERENCES operator (operatorId),
    CONSTRAINT fk_accessRecords_outOperatorId FOREIGN KEY (outOperatorId) REFERENCES operator (operatorId),
    CONSTRAINT fk_accessRecords_orderId FOREIGN KEY (orderId) REFERENCES parking_reservation (orderId)
) comment '出入记录';





