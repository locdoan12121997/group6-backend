DROP DATABASE register_db;
CREATE DATABASE register_db;
USE register_db;

CREATE TABLE Account(
	user_name   	VARCHAR(255) NOT NULL,
	user_password   VARCHAR(255) NOT NULL,
	first_name  	VARCHAR(255) NOT NULL,
	last_name   	VARCHAR(255) NOT NULL,
	id          	INTEGER NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id)
);

CREATE TABLE Student(
	account_id  	INTEGER NOT NULL,
	code        	VARCHAR(255) NOT NULL,
	id          	INTEGER NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id),
	FOREIGN KEY (account_id) REFERENCES Account(id),
	UNIQUE(code)
);

CREATE TABLE Lecturer(
	account_id  	INTEGER NOT NULL,
	id          	INTEGER NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id),
	FOREIGN KEY (account_id) REFERENCES Account(id)
);

CREATE TABLE Assistant(
	account_id  	INTEGER NOT NULL,
	id          	INTEGER NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id),
	FOREIGN KEY (account_id) REFERENCES Account(id)
);

CREATE TABLE Semester(
	from_time   	DATE	NOT NULL,
	to_time     	DATE	NOT NULL,
	id          	INTEGER NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id)
);

CREATE TABLE Module(
	id          	INTEGER NOT NULL AUTO_INCREMENT,
	code        	VARCHAR(20) UNIQUE NOT NULL,
	name 	VARCHAR(100) NOT NULL,
	semester_id 	INTEGER NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (code, name, semester_id),
	FOREIGN KEY (semester_id) REFERENCES Semester(id)
);

CREATE TABLE ModuleSession(
	date_of_session DATE	NOT NULL,
	from_time   	TIME	NOT NULL,
	to_time     	TIME NOT NULL,
	id          	INTEGER NOT NULL AUTO_INCREMENT,
	module_id   	INTEGER NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (module_id ) REFERENCES Module(id)
);

CREATE TABLE Attendance(
	student_id  	INTEGER NOT NULL,
	session_id  	INTEGER NOT NULL,
	PRIMARY KEY (student_id, session_id),
	FOREIGN KEY (student_id) REFERENCES Student(id),
	FOREIGN KEY (session_id ) REFERENCES ModuleSession(id)
);

CREATE TABLE Exam(
	date_of_exam	DATE	NOT NULL,
	from_time   	TIME	NOT NULL,
	to_time     	TIME	NOT NULL,
	deadline    	DATE	NOT NULL,
	id          	INTEGER NOT NULL AUTO_INCREMENT,
	module_id   	INTEGER NOT NULL,
	FOREIGN KEY (module_id) REFERENCES Module(id),
	PRIMARY KEY (id)
);

CREATE TABLE ModuleList(
	student_id  	INTEGER NOT NULL,
	module_id   	INTEGER NOT NULL,
	PRIMARY KEY (student_id, module_id),
	FOREIGN KEY (student_id) REFERENCES Student(id),
	FOREIGN KEY (module_id) REFERENCES Module(id)
);

CREATE TABLE RegisterList(
	student_id  	INTEGER NOT NULL,
	exam_id   	INTEGER NOT NULL,
	PRIMARY KEY (student_id, exam_id),
	FOREIGN KEY (student_id) REFERENCES Student(id),
	FOREIGN KEY (exam_id ) REFERENCES Exam(id)
);

CREATE TABLE LecturerModule(
	lecturer_id  	INTEGER NOT NULL,
	module_id   	INTEGER NOT NULL,
	PRIMARY KEY (lecturer_id, module_id),
	FOREIGN KEY (lecturer_id ) REFERENCES Lecturer(id),
	FOREIGN KEY (module_id  ) REFERENCES Module(id)
);


INSERT INTO
	Account (user_name, user_password, first_name, last_name)
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

INSERT INTO
	Student (account_id, code)
VALUES
	(1, "fHUT4L"),
	(3, "7b2bpk"),
	(5, "krWMjW"),
	(7, "N4kwrb"),
	(9, "rQqGW6"),
	(11, "rasdf6");

INSERT INTO
	Lecturer(account_id)
VALUES
	(2),
	(6),
	(12);

INSERT INTO
	Assistant(account_id)
VALUES
	(4),
	(6);

INSERT INTO
	Semester(from_time, to_time)
VALUES
	('2012-09-01', '2013-01-31'),
	('2013-03-01', '2013-07-31'),
	('2013-09-01', '2014-01-31'),
	('2014-03-01', '2014-07-31'),
	('2014-09-01', '2015-01-31'),
	('2015-03-01', '2015-07-31'),
	('2015-09-01', '2016-01-31'),
	('2016-03-01', '2016-07-31');

INSERT INTO
	Module(code, name, semester_id)
VALUES
	("Pro12", "Programming", 1),
	("Sta12", "Statistics", 2),
	("Pro13", "Programming", 3),
	("Sta13", "Statistics", 4),
	("Pro14", "Programming", 5),
	("Sta14", "Statistics", 6),
	("Pro15", "Programming", 7),
	("Sta15", "Statistics", 8);

INSERT INTO
	ModuleSession(date_of_session, from_time, to_time, module_id)
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

INSERT INTO
	Attendance(student_id, session_id)
VALUES
	(1, 1),	(1, 2),	(1, 3),	(1, 4),	(1, 7),	(1, 8),	(1, 10),	(1, 12),
	(2, 1),	(2, 3),	(2, 5),	(2, 7),	(2, 9),	(2, 11),
	(3, 2),	(3, 4),	(3, 6),	(3, 8),	(3, 10),	(3, 12),
	(4, 2),	(4, 4),	(4, 5),	(4, 8),	(4, 9),	(4, 11),
	(5, 1),	(5, 5),	(5, 6),	(5, 8),	(5, 10),	(5, 13);

INSERT INTO
	Exam(date_of_exam, from_time, to_time, deadline, module_id)
VALUES
	('2013-02-01', '08:45:00', '12:00:00', '2013-01-20', 1),
	('2014-02-01', '08:45:00', '12:00:00', '2014-01-20', 2),
	('2015-02-01', '08:45:00', '12:00:00', '2015-01-20', 3),
	('2016-02-01', '08:45:00', '12:00:00', '2016-01-20', 4);

INSERT INTO
	ModuleList(student_id, module_id)
VALUES
	(1, 1), (1, 2),
	(2, 1), (2, 2),
	(3, 1), (3, 2),
	(4, 1), (4, 2),
	(5, 1), (5, 2);

INSERT INTO
	RegisterList(student_id, exam_id)
VALUES
	(1, 1), (1, 2),
	(2, 1),
	(3, 1),
	(4, 2),
	(5, 2);

INSERT INTO
	LecturerModule(lecturer_id, module_id)
VALUES
	(1, 1), (1, 3), (1, 5), (1, 7),
	(2, 2), (2, 4), (2, 6), (2, 8);

DELIMITER $$

--  Update an information of a course

DROP PROCEDURE IF EXISTS UpdateModule;
CREATE PROCEDURE UpdateModule(IN moduleId INTEGER, IN moduleCode VARCHAR(20), IN moduleName VARCHAR(100))
BEGIN
	UPDATE Module
	SET code = moduleCode, name = moduleName
	WHERE id = moduleId;
END$$

--  Get all modules
DROP PROCEDURE IF EXISTS GetModules;
CREATE PROCEDURE GetModules()
BEGIN
	SELECT * FROM Module;
END$$

--  Delete a module
DROP PROCEDURE IF EXISTS DeleteModule;
CREATE PROCEDURE DeleteModule(IN moduleId INTEGER)
BEGIN
	DELETE FROM Module WHERE id = moduleId;
END$$

--  Lecturer view module list he teach
DROP PROCEDURE IF EXISTS GetLectureModule;
CREATE PROCEDURE GetLectureModule(IN lecturerId INTEGER)
	BEGIN
	SELECT * FROM Module
	JOIN LecturerModule ON Module.id = LecturerModule.module_id
	WHERE lecturer_id = lecturerId;
END$$

--  Lecturer and Assistant view an attending list with a session
DROP PROCEDURE IF EXISTS GetAttendanceList;
CREATE PROCEDURE GetAttendanceList(IN sessionId INTEGER)
	BEGIN
	SELECT Student.*, Account.* from Student
	JOIN Attendance on Student.id = Attendance.student_id
	JOIN Account ON Student.account_id = Account.id
	WHERE Attendance.session_id = sessionId;
END$$

--  Lecturer and Assistant view an register list with the exam id
DROP PROCEDURE IF EXISTS GetRegisterByExamId;
CREATE PROCEDURE GetRegisterByExamId(IN examId INTEGER)
BEGIN
	SELECT Student.*, Account.* from Student
	JOIN RegisterList ON Student.account_id = RegisterList.student_id
	JOIN Account ON Student.id = Account.id
	WHERE RegisterList.exam_id = examId;
END$$

--  Student login
DROP PROCEDURE IF EXISTS LoginStudent;
CREATE PROCEDURE LoginStudent(IN userName VARCHAR(255), IN userPassword VARCHAR(255))
BEGIN
	SELECT Student.*, Account.*
	FROM Student
	JOIN Account ON Student.account_id = Account.id
	WHERE Account.user_name = userName
	AND Account.user_password = userPassword;
END$$

--  Lecturer login
DROP PROCEDURE IF EXISTS LoginLecturer;
CREATE PROCEDURE LoginLecturer(IN userName VARCHAR(255), IN userPassword VARCHAR(255))
BEGIN
	SELECT Lecturer.*, Account.*
	FROM Lecturer
	JOIN Account ON Lecturer.account_id = Account.id
	WHERE Account.user_name = userName
	AND Account.user_password = userPassword;
END$$

--  Assistant login
DROP PROCEDURE IF EXISTS LoginAssistant;
CREATE PROCEDURE LoginAssistant(IN userName VARCHAR(255), IN userPassword VARCHAR(255))
	BEGIN
	SELECT Assistant.*, Account.*
	FROM Assistant
	JOIN Account ON Assistant.account_id = Account.id
	WHERE Account.user_name = userName
	AND Account.user_password = userPassword;
END$$

--  Student can view the module enrollment list
DROP PROCEDURE IF EXISTS GetEnrollmentListByStudent;
CREATE PROCEDURE GetEnrollmentListByStudent(IN moduleId INTEGER)
BEGIN
	SELECT Student.*, Account.*
	FROM Student
	JOIN ModuleList ON Student.id = ModuleList.student_id
	JOIN Account ON Student.account_id = Account.id
	WHERE ModuleList.module_id = moduleId;
END$$

--  Update exam date/time
DROP PROCEDURE IF EXISTS UpdateExam;
CREATE PROCEDURE UpdateExam(IN examId INTEGER, IN examDate Date, IN examFromTime TIME, IN examToTime TIME)
BEGIN
	UPDATE Exam
	SET date_of_exam = examDate, from_time = examFromTime, to_time = examToTime
	WHERE id = examId;
END$$
--  Delete an exam
DROP PROCEDURE IF EXISTS DeleteExam;
CREATE PROCEDURE DeleteExam(IN examId INTEGER)
BEGIN
	DELETE
	FROM Exam
	WHERE id = examId;
END$$

--  Get all exams
DROP PROCEDURE IF EXISTS GetExams;
CREATE PROCEDURE GetExams()
BEGIN
	SELECT *
	FROM Exam;
END$$

--  Get register list for all students in all modules
DROP PROCEDURE IF EXISTS GetAllRegister;
CREATE PROCEDURE GetAllRegister()
BEGIN
	SELECT student_id, first_name, last_name, Module.name, Module.code
	FROM Student
	JOIN RegisterList ON Student.id = RegisterList.student_id
	JOIN Account ON Student.account_id = Account.id
	JOIN Exam ON RegisterList.exam_id = Exam.id
	JOIN Module ON Exam.module_id = Module.id;
END$$

--  Delete a module for a student
DROP PROCEDURE IF EXISTS DeleteModuleOfStudent;
CREATE PROCEDURE DeleteModuleOfStudent(IN studentId INTEGER, IN moduleId INTEGER)
BEGIN
	DELETE
	FROM ModuleList
	WHERE student_id = studentId AND module_id = moduleId;
END$$

-- Get module list for all students in all modules
DROP PROCEDURE IF EXISTS GetModuleList;
CREATE PROCEDURE GetModuleList()
BEGIN
	SELECT student_id, first_name, last_name, Module.name, Module.code
	FROM ModuleList
	JOIN Student ON ModuleList.student_id = Student.id
	JOIN Account ON Student.account_id = Account.id
	JOIN Module ON Module.id = ModuleList.module_id;
END$$

-- Update student code
DROP PROCEDURE IF EXISTS UpdateStudentCode;
CREATE PROCEDURE UpdateStudentCode(IN studentId INTEGER, IN studentCode VARCHAR(255))
BEGIN
	UPDATE Student
	SET code = studentCode
	WHERE id = 1;
END$$

-- Delete a student from the system
DROP PROCEDURE IF EXISTS DeleteStudent;
CREATE PROCEDURE DeleteStudent(IN studentId INTEGER)
BEGIN
	DELETE
	FROM Student
	WHERE id = studentId;
END$$

-- Delete a lecturer from the system
DROP PROCEDURE IF EXISTS DeleteLecturer;
CREATE PROCEDURE DeleteLecturer(IN lecturerId INTEGER)
BEGIN
	DELETE
	FROM Lecturer
	WHERE id = lecturerId;
END$$

-- Get student account list
DROP PROCEDURE IF EXISTS GetStudentList;
CREATE PROCEDURE GetStudentList()
BEGIN
	SELECT account_id, code AS student_code, user_name, user_password, first_name, last_name
	FROM Student
	JOIN Account ON Student.account_id = Account.id;
END$$

-- Get lecturer account list
DROP PROCEDURE IF EXISTS GetLecturerList;
CREATE PROCEDURE GetLecturerList()
BEGIN
	SELECT account_id, user_name, user_password, first_name, last_name
	FROM Lecturer
	JOIN Account ON Lecturer.account_id = Account.id;
END$$

-- View the details of a module
DROP PROCEDURE IF EXISTS GetModuleById;
CREATE PROCEDURE GetModuleById(
  IN moduleId INTEGER
)
BEGIN
SELECT * FROM Module where id = moduleId;
END$$


-- Register an exam for a student
DROP PROCEDURE IF EXISTS CreateRegister;
CREATE PROCEDURE CreateRegister(
  IN studentId INTEGER,
  IN examId INTEGER
)
BEGIN
INSERT INTO RegisterList(student_id, exam_id)
VALUES (studentId, examId);
END$$

-- Unregister
DROP PROCEDURE IF EXISTS DeleteRegister;
CREATE PROCEDURE DeleteRegister(
  IN studentId INTEGER,
  IN examId INTEGER
)
BEGIN
DELETE FROM RegisterList
WHERE student_id = studentId AND exam_id = examId;
END$$


-- Sign the attendance list
DROP PROCEDURE IF EXISTS CreateAttendance;
CREATE PROCEDURE CreateAttendance(
  IN studentId INTEGER,
  IN sessionId INTEGER
)
BEGIN
INSERT INTO Attendance(student_id, session_id)
VALUES (studentId, sessionId);
END$$


-- Unsign
DROP PROCEDURE IF EXISTS DeleteAttendance;
CREATE PROCEDURE DeleteAttendance(
  IN studentId INTEGER,
  IN sessionId INTEGER
)
BEGIN
DELETE FROM Attendance
WHERE student_id = studentId AND session_id = sessionId;
END$$

-- Create semester
DROP PROCEDURE IF EXISTS CreateSemester;
CREATE PROCEDURE CreateSemester(
  IN fromTime DATE,
  IN toTime DATE
)
BEGIN
INSERT INTO
	Semester(from_time, to_time)
VALUES
	(fromTime, toTime);
END$$

-- Get list of semester
DROP PROCEDURE IF EXISTS CreateSemester;
CREATE PROCEDURE GetSemesters()
BEGIN
SELECT * FROM Semester;
END$$

-- Delete semester by Id
DROP PROCEDURE IF EXISTS DeleteSemester;
CREATE PROCEDURE DeleteSemester(
  IN semesterId INTEGER
)
BEGIN
	DELETE FROM Semester WHERE id = semesterId;
END$$

-- Update semester by Id
DROP PROCEDURE IF EXISTS UpdateSemester;
CREATE PROCEDURE UpdateSemester(
  IN semesterId INTEGER,
  IN from_time DATE,
  IN to_time DATE
)
BEGIN
	UPDATE Semester
	SET fromTime = from_time, toTime = to_time
	WHERE id = semesterId;
END$$


-- List all the exams for a given semester
DROP PROCEDURE IF EXISTS GetExamBySemesterId;
CREATE PROCEDURE GetExamBySemesterId(
  IN semesterId INTEGER
)
BEGIN
SELECT date_of_exam, Exam.from_time, Exam.to_time, deadline, code, name, Semester.from_time AS Semester_from_time, Semester.to_time AS Semester_to_time
FROM Exam JOIN Module ON Exam.module_id = Module.id
JOIN Semester ON Module.semester_id = Semester.id
Where Semester.id = semesterId;
END$$

-- List all the modules that a given student has enrolled in
DROP PROCEDURE IF EXISTS GetModuleByStudentId;
CREATE PROCEDURE GetModuleByStudentId(
  IN studentId INTEGER
)
BEGIN
SELECT student_id, module_id, Module.code, Module.name, semester_id
FROM Student JOIN ModuleList ON ModuleList.student_id = Student.id
JOIN Module ON Module.id = ModuleList.module_id
WHERE Student.id = studentId;
END$$

--  List all overlap module
DROP PROCEDURE IF EXISTS GetOverlapModule;
CREATE PROCEDURE GetOverlapModule()
BEGIN
SELECT m1.*, m2.* FROM
(SELECT s1.module_id AS m1_id, s1.id AS 'Session 1 id', s1.date_of_session AS 'Session 1 Date', s1.from_time AS 'Start 1', s1.to_time AS 'End 1', s2.module_id AS m2_id, s2.id AS 'Session 2 id', s2.date_of_session AS 'Session 2 Date', s2.from_time AS 'Start 2', s2.to_time AS 'End 2'
FROM ModuleSession s1, ModuleSession s2
WHERE s1.id < s2.id
AND s1.date_of_session = s2.date_of_session
AND ((s1.from_time < s2.from_time AND s2.from_time < s1.to_time)
OR (s2.from_time < s1.from_time AND s1.from_time < s2.to_time))) as ss
JOIN Module m1 ON ss.m1_id = m1.id
JOIN Module m2 ON ss.m2_id = m2.id;
END$$

-- Change delimiter back to default
DELIMITER ;

/* CALL UpdateModule(1, '123abc', 'test');
CALL GetModules();
INSERT INTO Module(code, name, semester_id)
VALUES ("Pro", "Programming", 1);
CALL DeleteModule(9);
CALL GetLectureModule(1);
CALL GetAttendanceList(1);
CALL GetRegisterByExamId(1);
CALL LoginStudent('abc', '$#!#@');
CALL LoginLecturer('edf', 'D#RS%');
CALL LoginAssistant('ghi', 'AAAAA');
CALL GetEnrollmentListByStudent(1);
CALL UpdateExam(1, '2016-01-01', '08:45:00', '12:00:00');
INSERT INTO
	Exam(date_of_exam, from_time, to_time, deadline, module_id)
VALUES
	('2013-02-01', '08:45:00', '12:00:00', '2013-01-20', 1);
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
CALL GetModuleByStudentId(1);
CALL GetOverlapModule(); */
