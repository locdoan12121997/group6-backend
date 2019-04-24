DROP DATABASE IF EXISTS register_db;
CREATE DATABASE register_db;
USE register_db;


CREATE TABLE Account(
    user_name       VARCHAR(255) NOT NULL,
    user_password   VARCHAR(255) NOT NULL,
    first_name      VARCHAR(255) NOT NULL,
    last_name       VARCHAR(255) NOT NULL,
    id              INTEGER NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE Student(
    account_id      INTEGER NOT NULL,
    code            VARCHAR(255) NOT NULL,
    id              INTEGER NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES Account(id),
    UNIQUE(code)
);

CREATE TABLE Lecturer(
    account_id      INTEGER NOT NULL,
    id              INTEGER NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES Account(id)
);

CREATE TABLE Assistant(
    account_id      INTEGER NOT NULL,
    id              INTEGER NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES Account(id)
);

CREATE TABLE Semester(
    from_time       DATE    NOT NULL,
    to_time         DATE    NOT NULL,
    id              INTEGER NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE Module(
    id              INTEGER NOT NULL AUTO_INCREMENT,
    code            VARCHAR(20) UNIQUE NOT NULL,
    name     VARCHAR(100) NOT NULL,
    semester_id     INTEGER NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (code, name, semester_id),
    FOREIGN KEY (semester_id) REFERENCES Semester(id)
);

CREATE TABLE ModuleSession(
    date_of_session DATE    NOT NULL,
    from_time       TIME    NOT NULL,
    to_time         TIME NOT NULL,
    id              INTEGER NOT NULL AUTO_INCREMENT,
    module_id       INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (module_id ) REFERENCES Module(id)
);

CREATE TABLE Attendance(
    student_id      INTEGER NOT NULL,
    session_id      INTEGER NOT NULL,
    PRIMARY KEY (student_id, session_id),
    FOREIGN KEY (student_id) REFERENCES Student(id),
    FOREIGN KEY (session_id ) REFERENCES ModuleSession(id)
);
/*                     ------------------              */

CREATE TABLE Exam(
    date_of_exam    DATE    NOT NULL,
    from_time       TIME    NOT NULL,
    to_time         TIME    NOT NULL,
    deadline        DATE    NOT NULL,
    id              INTEGER NOT NULL AUTO_INCREMENT,
    module_id       INTEGER NOT NULL,
    FOREIGN KEY (module_id) REFERENCES Module(id),
    PRIMARY KEY (id)
);

CREATE TABLE ModuleList(
    student_id      INTEGER NOT NULL,
    module_id       INTEGER NOT NULL,
    PRIMARY KEY (student_id, module_id),
    FOREIGN KEY (student_id) REFERENCES Student(id),
    FOREIGN KEY (module_id) REFERENCES Module(id)
);

CREATE TABLE RegisterList(
    student_id      INTEGER NOT NULL,
    exam_id       INTEGER NOT NULL,
    PRIMARY KEY (student_id, exam_id),
    FOREIGN KEY (student_id) REFERENCES Student(id),
    FOREIGN KEY (exam_id ) REFERENCES Exam(id)
);

CREATE TABLE LecturerModule(
    lecturer_id      INTEGER NOT NULL,
    module_id       INTEGER NOT NULL,
    PRIMARY KEY (lecturer_id, module_id),
    FOREIGN KEY (lecturer_id ) REFERENCES Lecturer(id),
    FOREIGN KEY (module_id  ) REFERENCES Module(id)
);

INSERT INTO Account (user_name, user_password, first_name, last_name)
VALUES
    ("Wallace", 6945, "Mooney", "Halee"),
    ("Darrel", 6104, "Burris", "Ferris"),
    ("Kathleen", 9660, "Mcpherson", "Maile"),
    ("Riley", 8480, "Heath", "Rhiannon"),
    ("Lars", 5689, "Bruce", "Susan"),
    ("Zeph", 7516, "Martinez", "Rose"),
    ("Price", 5747, "Vance", "Philip"),
    ("Uta", 8746, "Salazar", "Allistair"),
    ("Maryam", 6528, "Tyson", "May"),
    ("Jack", 9948, "Navarro", "Joan"),
    ("Mya", 948, "Navro", "Jon"),
    ("Mam", 9588, "Tyn", "My");

INSERT INTO Student (account_id, code)
VALUES
    (1, "fHUT4L"),
    (3, "7b2bpk"),
    (5, "krWMjW"),
    (7, "N4kwrb"),
    (9, "rQqGW6"),
    (11, "rasdf6");

INSERT INTO Lecturer(account_id)
VALUES
    (2),
    (6),
    (12);

INSERT INTO Assistant(account_id)
VALUES
    (4),
    (6);

INSERT INTO Semester(from_time, to_time)
VALUES
    ('2012-09-01', '2013-01-31'),
    ('2013-03-01', '2013-07-31'),
    ('2013-09-01', '2014-01-31'),
    ('2014-03-01', '2014-07-31'),
    ('2014-09-01', '2015-01-31'),
    ('2015-03-01', '2015-07-31'),
    ('2015-09-01', '2016-01-31'),
    ('2016-03-01', '2016-07-31');

INSERT INTO Module(code, name, semester_id)
VALUES
    ("Pro12", "Programming", 1),
    ("Sta12", "Statistics", 2),
    ("Pro13", "Programming", 3),
    ("Sta13", "Statistics", 4),
    ("Pro14", "Programming", 5),
    ("Sta14", "Statistics", 6),
    ("Pro15", "Programming", 7),
    ("Sta15", "Statistics", 8);

INSERT INTO ModuleSession(date_of_session, from_time, to_time, module_id)
VALUES
    ('2012-09-01', '08:45:00', '12:00:00', 1),
    ('2012-09-01', '13:00:00', '16:15:00', 1),
    ('2012-11-01', '08:45:00', '12:00:00', 1),
    ('2012-11-01', '13:00:00', '16:15:00', 1),
    ('2013-01-01', '08:45:00', '12:00:00', 1),
    ('2013-01-01', '13:00:00', '16:15:00', 1),
    ('2015-03-01', '08:45:00', '12:00:00', 2),
    ('2015-03-01', '13:00:00', '16:15:00', 2),
    ('2015-05-01', '08:45:00', '12:00:00', 2),
    ('2015-05-01', '13:00:00', '16:15:00', 2),
    ('2015-07-01', '08:45:00', '12:00:00', 2),
    ('2015-07-01', '13:00:00', '16:15:00', 2),
    ('2012-06-01', '11:59:00', '12:01:00', 2);

INSERT INTO Attendance(student_id, session_id)
VALUES
    (1, 1),    (1, 2),    (1, 3),    (1, 4),    (1, 7),    (1, 8),    (1, 10),    (1, 12),
    (2, 1),    (2, 3),    (2, 5),    (2, 7),    (2, 9),    (2, 11),
    (3, 2),    (3, 4),    (3, 6),    (3, 8),    (3, 10),    (3, 12),
    (4, 2),    (4, 4),    (4, 5),    (4, 8),    (4, 9),    (4, 11),
    (5, 1),    (5, 5),    (5, 6),    (5, 8),    (5, 10),    (5, 13);

INSERT INTO Exam(date_of_exam, from_time, to_time, deadline, module_id)
VALUES
    ('2013-02-01', '08:45:00', '12:00:00', '2013-01-20', 1),
    ('2014-02-01', '08:45:00', '12:00:00', '2014-01-20', 2),
    ('2015-02-01', '08:45:00', '12:00:00', '2015-01-20', 3),
    ('2016-02-01', '08:45:00', '12:00:00', '2016-01-20', 4);

INSERT INTO ModuleList(student_id, module_id)
VALUES
    (1, 1), (1, 2),
    (2, 1), (2, 2),
    (3, 1), (3, 2),
    (4, 1), (4, 2),
    (5, 1), (5, 2);

INSERT INTO RegisterList(student_id, exam_id)
VALUES
    (1, 1), (1, 2),
    (2, 1),
    (3, 1),
    (4, 2),
    (5, 2);

INSERT INTO LecturerModule(lecturer_id, module_id)
VALUES
    (1, 1), (1, 3), (1, 5), (1, 7),
    (2, 2), (2, 4), (2, 6), (2, 8);




DELIMITER $$













-- Change delimiter back to default
DELIMITER ;
/*
CALL UpdateModule(1, '123abc', 'test');
CALL GetModules();
INSERT INTO Module(code, name, semester_id)
VALUES ("Pro", "Programming", 1);
CALL DeleteModule(9);
CALL GetLectureModule(1);
CALL GetAttendanceListBySessionId(1);
CALL GetRegisterByExamId(1);
CALL FindStudentAccount('abc', '$#!#@');
CALL FindLecturerAccount('edf', 'D#RS%');
CALL FindAssistantAccount('ghi', 'AAAAA');
CALL GetEnrollmentListByStudent(1);
CALL UpdateExam(1, '2016-01-01', '08:45:00', '12:00:00');
INSERT
INTO Exam(date_of_exam, from_time, to_time, deadline, module_id)
VALUES ('2013-02-01', '08:45:00', '12:00:00', '2013-01-20', 1);
CALL DeleteExam(5);
CALL GetExams();
CALL GetAllRegister();
CALL DeleteModuleOfStudent(1, 1);
CALL GetModuleList();
CALL UpdateStudentCode(1, 'AAA');
CALL DeleteStudent(6);
CALL DeleteLecturer(3);
CALL GetStudentList();
CALL GetLecturerList();
CALL GetModuleById(1);
CALL CreateRegister(2, 2);
CALL DeleteRegister(2, 2);
CALL CreateAttendance(3, 1);
CALL DeleteAttendance(3, 1);
CALL GetExamBySemesterId(1);
CALL GetModulesByStudentId(1);
CALL GetOverlapModule();
CALL GetRegisterByStudentId(1);*/