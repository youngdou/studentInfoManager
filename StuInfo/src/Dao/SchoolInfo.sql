CREATE DATABASE SchoolInfo;
use SchoolInfo;
ALTER DATABASE CHARACTER SET "utf8";
#
# DROP TABLE IF EXISTS academy;
# CREATE TABLE academy (
#     acaId VARCHAR(3) NOT NULL PRIMARY KEY ,
#     acaName VARCHAR(20) NOT NULL,
#     dean VARCHAR(10) NOT NULL
# );

DROP TABLE IF EXISTS student;
CREATE TABLE student (
    stuId VARCHAR(12) NOT NULL  PRIMARY KEY,
    name VARCHAR(10) NOT NULL ,
    sex VARCHAR(1) NOT NULL ,
    birthDay DATE NOT NULL ,
    acaName VARCHAR(20),
    master VARCHAR(10)
);

DROP TABLE IF EXISTS course;
CREATE TABLE course (
    courseId VARCHAR(10) NOT NULL  PRIMARY KEY,
    name VARCHAR(100) NOT NULL ,
    timeLen INT NOT NULL,
    credit INT NOT NULL,
    teacherName VARCHAR(10),
    Location VARCHAR(100),
    courseTime VARCHAR(100)

);
INSERT INTO course VALUES("C01", "数据库",48,4,"林育蓓", "here", "周五9-11");
INSERT INTO course VALUES("C02", "数值计算",58,4,"juan", "here", "周五12-15");
INSERT INTO course VALUES("C03", "计算机网络",18,4,"young", "here", "周三12-15");


DROP TABLE IF EXISTS teacher;
CREATE TABLE teacher (
    id INT NOT NULL  PRIMARY KEY,
    name VARCHAR(10) NOT NULL,
    sex VARCHAR(1) NOT NULL,
    phone VARCHAR(20) NOT NULL ,
    degree VARCHAR(10) NOT NULL ,
    job VARCHAR(10) NOT NULL ,
    acaName VARCHAR(20) NOT NULL
);

DROP TABLE IF EXISTS score;
CREATE TABLE score (
    stuId VARCHAR(12) NOT NULL PRIMARY KEY,
    stuName VARCHAR(100) NOT NULL ,
    courseId VARCHAR(10) NOT NULL,
    courseName VARCHAR(100) NOT NULL,
    credit INT NOT NULL,
    usualScore INT NOT NULL ,
    endScore INT NOT NULL ,
    symScore INT NOT NULL
);
INSERT INTO score VALUES("111111", "luofenchang", "C01", "数据库", 4, 60, 60, 100);

DROP TABLE IF EXISTS courseSelect;
CREATE TABLE courseSelect (
    courseId VARCHAR(12) NOT NULL ,
    stuId VARCHAR(10) NOT NULL
);
INSERT INTO courseSelect VALUES ("C01", "111111");

# 内连接
SELECT * FROM (SELECT c.courseId, c.name, c.timeLen, c.credit, c.teacherName,c.Location,c.courseTime, cs.stuId
               FROM course c LEFT JOIN courseSelect cs ON c.courseId = cs.courseId) AS newcols WHERE stuId = NULL;