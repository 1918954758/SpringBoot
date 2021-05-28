DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);


CREATE TABLE testdb.`user`
(
    `id` BIGINT(20) primary key not null auto_increment COMMENT '主键ID',
    `name` VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    `age` INT(11) NULL DEFAULT NULL COMMENT '年龄',
    `email` VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱'
)ENGINE=InnoDB CHARSET=utf8;

DELETE FROM user;

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');

INSERT INTO user (id, name, age, email) VALUES
(null, 'Jone', 18, 'test1@baomidou.com'),
(null, 'Jack', 20, 'test2@baomidou.com'),
(null, 'Tom', 28, 'test3@baomidou.com'),
(null, 'Sandy', 21, 'test4@baomidou.com'),
(null, 'Billie', 23, 'test5@baomidou.com'),
(null, 'Filip', 24, 'test6@baomidou.com'),
(null, 'Asckeyi', 26, 'test7@baomidou.com'),
(null, 'Cicco', 27, 'test8@baomidou.com'),
(null, 'Pilisdect', 22, 'test9@baomidou.com'),
(null, 'Cedadti', 27, 'test10@baomidou.com'),
(null, 'Wilii', 23, 'test11@baomidou.com'),
(null, 'Quoo', 21, 'test12@baomidou.com');



-- 给已存在字段设置自增属性
alter table user modify id bigint auto_increment;

-- 给自增字段设置初始值
alter table user auto_increment=0;
-- CREATE TABLE `user`
-- (
--     `id`    bigint NOT NULL AUTO_INCREMENT,
--     `name`  varchar(30) DEFAULT NULL COMMENT '姓名',
--     `age`   int         DEFAULT NULL COMMENT '年龄',
--     `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
--     PRIMARY KEY (`id`)
-- )ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;