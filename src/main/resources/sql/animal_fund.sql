SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

drop table if exists `user`;
create table if not exists `user`
(
    `id`               bigint unsigned auto_increment comment '用户 ID',
    `user_mobile`      varchar(20)      null comment '用户手机号',
    `user_email`       varchar(50)      null comment '用户邮箱',
    `user_salt`        varchar(10)      not null comment '用户盐',
    `user_password`    varchar(100)     not null comment '用户密码',
    `user_full_name`   varchar(20)      not null comment '用户姓名',
    `user_nick_name`   varchar(20)      not null comment '用户昵称',
    `user_avatar`      varchar(100)     not null comment '用户头像',
    `user_gender`      varchar(10)      not null comment '用户性别[男:MAN,女:WOMAN',
    `user_age`         tinyint unsigned not null comment '用户年龄 [0-150]',
    `user_birth`       datetime         not null comment '用户生日[yyyy-MM-dd]',
    `user_id_card`     varchar(20)      not null comment '用户身份证',
    `user_id_card_img` varchar(100)     null comment '用户身份证明',
    `examine_status`   varchar(20)      not null comment '审核状态[待审核: UN_EXAMINE, 审核通过: EXAMINE_SUCCESS, 审核失败:EXAMINE_FAIL]',
    `user_sign`        varchar(50)      not null comment '用户个性签名',
    `create_time`      datetime         not null comment '创建时间',
    `update_time`      datetime         not null comment '更新时间',
    `version`          int unsigned     not null default 1 comment '乐观锁',
    `del_flag`         tinyint          not null default 0 comment '逻辑删除',

    primary key (`id`) using btree,
    unique index `unique_idx` (`user_email`, `user_mobile`) using btree,
    index `common_idx` (`user_full_name`, `user_nick_name`) using btree
) engine = InnoDB
  character set = utf8
  auto_increment = 1000000000
  collate = utf8_bin
  row_format = dynamic comment = '用户表';

insert into user(id, user_mobile, user_email, user_salt, user_password, user_full_name, user_nick_name, user_avatar,
                 user_gender, user_age, user_birth, user_id_card, user_id_card_img, examine_status, user_sign,
                 create_time, update_time, version, del_flag)
values (1000000001, null, null, 'awfhteshuf', '36EF698E7F2F93BF87476DBE46714C17',
        'admin', 'admin', '', 'MAN', 18, '2001-01-01',
        '', '', 'EXAMINE_SUCCESS', '', now(), now(),
        1, 0);

drop table if exists `role`;
create table if not exists `role`
(
    `id`          bigint unsigned auto_increment comment '角色 ID',
    `role_name`   varchar(20)  not null comment '角色名',
    `create_time` datetime     not null comment '创建时间',
    `update_time` datetime     not null comment '更新时间',
    `version`     int unsigned not null default 1 comment '乐观锁',
    `del_flag`    tinyint      not null default 0 comment '逻辑删除',

    primary key (`id`) using btree,
    unique index `role_name` (`role_name`) using btree
) engine = InnoDB
  character set = utf8
  auto_increment = 1000000000
  collate = utf8_bin
  row_format = dynamic comment = '角色表';

insert into `role` (id, role_name, create_time, update_time, version, del_flag)
values (1000000001, 'ADMIN', now(), now(), 1, 0),
       (1000000002, 'VOLUNTEER', now(), now(), 1, 0),
       (1000000003, 'USER', now(), now(), 1, 0);

drop table if exists `user_role`;
create table if not exists `user_role`
(
    `id`          bigint unsigned auto_increment comment '主键 ID',
    `user_id`     bigint unsigned not null comment '用户 ID',
    `role_id`     bigint unsigned not null comment '角色 ID',
    `create_time` datetime        not null comment '创建时间',
    `update_time` datetime        not null comment '更新时间',
    `version`     int unsigned    not null default 1 comment '乐观锁',
    `del_flag`    tinyint         not null default 0 comment '逻辑删除',

    primary key (`id`) using btree,
    index `user_role_id` (`user_id`, `role_id`) using btree
) engine = InnoDB
  character set = utf8
  auto_increment = 1000000000
  collate = utf8_bin
  row_format = dynamic comment = '用户角色对应表';

insert into user_role(id, user_id, role_id, create_time, update_time, version, del_flag)
values (1000000001, 1000000001, 1000000001, now(), now(), 1, 0),
       (1000000002, 1000000001, 1000000002, now(), now(), 1, 0),
       (1000000003, 1000000001, 1000000003, now(), now(), 1, 0);


drop table if exists `rescue_animal_info`;
create table if not exists `rescue_animal_info`
(
    `id`             bigint unsigned auto_increment comment '主键 ID',
    `user_id`        bigint unsigned                          not null comment '申请用户 ID',
    `animal_type`    varchar(20)                              not null comment '动物种类',
    `animal_gender`  varchar(10)                              not null comment '动物性别[雄性:MALE, 雌性:FEMALE]',
    `injury_degree`  varchar(10)                              not null comment '受伤程度[]',
    `address`        varchar(30)                              not null comment '地点',
    `address_detail` varchar(50)                              not null comment '具体地点',
    `apply_reason`   varchar(50)                              not null comment '申请原因',
    `apply_img`      text CHARACTER SET utf8 COLLATE utf8_bin null comment '申请照片',
    `examine_status` varchar(10)                              not null comment '审核状态[待审核: UN_EXAMINE, 审核通过: EXAMINE_SUCCESS, 审核失败:EXAMINE_FAIL]',
    `create_time`    datetime                                 not null comment '创建时间',
    `update_time`    datetime                                 not null comment '更新时间',
    `version`        int unsigned                             not null default 1 comment '乐观锁',
    `del_flag`       tinyint                                  not null default 0 comment '逻辑删除',

    primary key (`id`) using btree,
    index `user_idx` (`user_id`) using btree,
    index `injury_degree_idx` (`injury_degree`) using btree

) engine = InnoDB
  character set = utf8
  auto_increment = 1000000000
  collate = utf8_bin
  row_format = dynamic comment = '救援动物信息表';

drop table if exists `rescue_animal_status`;
create table if not exists `rescue_animal_status`
(
    `id`                       bigint unsigned auto_increment comment '主键 ID',
    `rescue_id`                bigint unsigned                          not null comment '救援动物信息 ID',
    `user_id`                  bigint unsigned                          not null comment '救援用户 ID',
    `rescue_status`            varchar(20)                              not null comment '救援状态[未开始:UN_DO, 审核中: EXAMINING, 募款中:FUNDRAISING, 救援中: RESCUING, 救援成功: SUCCESS, 救援失败:FAIL]',
    `capital_budget`           decimal                                  not null comment '资金预算',
    `examine_status`           varchar(10)                              not null comment '审核状态[待审核: UN_EXAMINE, 审核通过: EXAMINE_SUCCESS, 审核失败:EXAMINE_FAIL]',
    `animal_before_rescue_img` text CHARACTER SET utf8 COLLATE utf8_bin not null comment '动物救援前情况(照片)',
    `animal_in_rescue_img`     text CHARACTER SET utf8 COLLATE utf8_bin not null comment '动物救援中情况(照片)',
    `animal_after_rescue_img`  text CHARACTER SET utf8 COLLATE utf8_bin not null comment '动物救援后情况(照片)',
    `create_time`              datetime                                 not null comment '创建时间',
    `update_time`              datetime                                 not null comment '更新时间',
    `version`                  int unsigned                             not null default 1 comment '乐观锁',
    `del_flag`                 tinyint                                  not null default 0 comment '逻辑删除',

    primary key (`id`) using btree,
    index `user_idx` (`user_id`) using btree,
    index `rescue_idx` (`rescue_id`) using btree

) engine = InnoDB
  character set = utf8
  auto_increment = 1000000000
  collate = utf8_bin
  row_format = dynamic comment = '救援动物状态表';

drop table if exists `rescue_animal_contribution`;
create table if not exists `rescue_animal_contribution`
(
    `id`                 bigint unsigned auto_increment comment '主键 ID',
    `rescue_id`          bigint unsigned not null comment '救援动物信息 ID',
    `user_id`            bigint unsigned not null comment '捐款用户 ID',
    `contribution_money` decimal         not null comment '捐款数额',
    `create_time`        datetime        not null comment '创建时间',
    `update_time`        datetime        not null comment '更新时间',
    `version`            int unsigned    not null default 0 comment '乐观锁',
    `del_flag`           tinyint         not null default 0 comment '逻辑删除',

    primary key (`id`) using btree,
    index `user_idx` (`user_id`) using btree,
    index `rescue_idx` (`rescue_id`) using btree
) engine = InnoDB
  character set = utf8
  auto_increment = 1000000000
  collate = utf8_bin
  row_format = dynamic comment = '救援动物捐款信息表';

drop table if exists `rescue_animal_capital_detail`;
create table if not exists `rescue_animal_capital_detail`
(
    `id`             bigint unsigned auto_increment comment '主键 ID',
    `rescue_id`      bigint unsigned not null comment '救援动物信息 ID',
    `capital_cost`   decimal         not null comment '资金花费',
    `capital_reason` varchar(100)    not null comment '资金明细',
    `capital_prove`  varchar(100)    not null comment '资金证明(照片)',
    `examine_status` varchar(10)     not null comment '审核状态[待审核: UN_EXAMINE, 审核通过: EXAMINE_SUCCESS, 审核失败:EXAMINE_FAIL]',
    `create_time`    datetime        not null comment '创建时间',
    `update_time`    datetime        not null comment '更新时间',
    `version`        int unsigned    not null default 1 comment '乐观锁',
    `del_flag`       tinyint         not null default 0 comment '逻辑删除',

    primary key (`id`) using btree,
    index `rescue_idx` (`rescue_id`) using btree
) engine = InnoDB
  character set = utf8
  auto_increment = 1000000000
  collate = utf8_bin
  row_format = dynamic comment = '救援动物资金明细表';

drop table if exists `comment_root`;
create table if not exists `comment_root`
(
    `id`          bigint unsigned auto_increment comment '主键 ID',
    `reply_id`    bigint unsigned null comment '回复留言板主键 ID',
    `user_id`     bigint unsigned not null comment '发送用户 ID',
    `message`     varchar(150)    not null comment '留言信息',
    `create_time` datetime        not null comment '创建时间',
    `update_time` datetime        not null comment '更新时间',
    `version`     int unsigned    not null default 1 comment '乐观锁',
    `del_flag`    tinyint         not null default 0 comment '逻辑删除',

    primary key (`id`) using btree,
    index `user_idx` (`user_id`) using btree
) engine = InnoDB
  character set = utf8
  auto_increment = 1000000000
  collate = utf8_bin
  row_format = dynamic comment = '留言板主表';

drop table if exists `comment_reply`;
create table if not exists `comment_reply`
(
    `id`          bigint unsigned auto_increment comment '主键 ID',
    `root_id`     bigint unsigned null comment '回复留言板主键 ID',
    `user_id`     bigint unsigned not null comment '发送用户 ID',
    `message`     varchar(150)    not null comment '留言信息',
    `create_time` datetime        not null comment '创建时间',
    `update_time` datetime        not null comment '更新时间',
    `version`     int unsigned    not null default 1 comment '乐观锁',
    `del_flag`    tinyint         not null default 0 comment '逻辑删除',

    primary key (`id`) using btree,
    index `user_idx` (`user_id`) using btree,
    index `root_idx` (`root_id`) using btree
) engine = InnoDB
  character set = utf8
  auto_increment = 1000000000
  collate = utf8_bin
  row_format = dynamic comment = '留言板回复表';

drop table if exists `comment_report`;
create table if not exists `comment_report`
(
    `id`            bigint unsigned auto_increment comment '主键 ID',
    `message_id`    bigint unsigned null comment '留言板主键 ID',
    `message_type`  varchar(10)     not null comment '举报留言类型[ROOT,REPLY]',
    `user_id`       bigint unsigned not null comment '举报用户 ID',
    `report_reason` varchar(200)    not null comment '举报原因',
    `report_status` varchar(150)    not null comment '举报状态[待审核: UN_EXAMINE, 审核通过: EXAMINE_SUCCESS, 审核失败:EXAMINE_FAIL]',
    `create_time`   datetime        not null comment '创建时间',
    `update_time`   datetime        not null comment '更新时间',
    `version`       int unsigned    not null default 1 comment '乐观锁',
    `del_flag`      tinyint         not null default 0 comment '逻辑删除',

    primary key (`id`) using btree,
    index `user_idx` (`user_id`) using btree
) engine = InnoDB
  character set = utf8
  auto_increment = 1000000000
  collate = utf8_bin
  row_format = dynamic comment = '留言板举报表';


