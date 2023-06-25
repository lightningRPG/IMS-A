use test;

drop table if exists j08l_score;
drop table if exists j08l_course;
drop table if exists j08l_student;
drop table if exists j08l_user;

# 用户表
create table j08l_user(
    `uid` varchar(30) primary key,
    `username` varchar(30) not null unique,
    `password` varchar(30) not null,
    `deleted` boolean not null default false
);

insert into j08l_user values
('test123456', 'test1', '123456', default),
('test123450', 'test2', '123456', default);

# 学生表
create table j08l_student(
    `sid` varchar(30) primary key,
    `name` varchar(30) not null,
    `classname` varchar(30) not null,
    `sex` varchar(10) not null
);

insert into j08l_student values
('2020108200206', '何正南', '数媒2班', '男'),
('2020108200207', '黄定坤', '数媒2班', '男'),
('2020108200208', '黄尚楠', '数媒2班', '男'),
('2020108200209', '黄曦', '数媒2班', '男'),
('2020108200210', '黄晓婷', '数媒2班', '女'),
('test123450', '李四', '数媒2班', '女'),
('test123452', '赵六', '数媒1班', '男');

# 课程表
create table j08l_course(
    `cid` varchar(30) primary key,
    `course_name` varchar(30) not null,
    `teacher_name` varchar(30) not null
);

insert into j08l_course values
('1', '大学英语', '李刚'),
('2', '计算机应用与技术', '刘姥姥');

# 成绩表
create table j08l_score(
    `sid` varchar(30) not null,
    `cid` varchar(30) not null,
    `score` float default 0,
    primary key (sid, cid),
    foreign key (sid) references j08l_student(sid) on delete cascade,
    foreign key (cid) references j08l_course(cid) on delete cascade
);

insert into j08l_score values
('2020108200208', '1', 66.2),
('2020108200206', '1', 77.0),
('2020108200207', '1', 89.5),
('2020108200207', '2', 90.5),
('2020108200208', '2', 58.5),
('2020108200206', '2', 100.0);

# 视图
drop view if exists j08l_complexData;
create view j08l_complexData as
    select
       j08l_student.sid,
       `classname`,
       `name`,
       `course_name`,
       `score`
    from
        j08l_student,
        j08l_course,
        j08l_score
    where j08l_score.sid = j08l_student.sid
      and j08l_score.cid = j08l_course.cid;
