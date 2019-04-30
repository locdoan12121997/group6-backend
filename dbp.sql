USE register_db;

DELIMITER $$

-- Create semester
CREATE PROCEDURE CreateSemester(IN fromTime DATE, IN toTime DATE)
BEGIN
    INSERT INTO Semester(from_time, to_time)
    VALUE	(fromTime, toTime);
END$$

-- Get list of semester
CREATE PROCEDURE GetSemesters()
BEGIN
    SELECT *
    FROM Semester;
END$$

-- Get student account list                                                   OK
CREATE PROCEDURE GetSemesterById(IN semesterId INTEGER)
BEGIN
    SELECT *
    FROM Semester
    WHERE Semester.id = semesterId;
END$$

-- Delete semester by Id
CREATE PROCEDURE DeleteSemester(IN semesterId INTEGER)
BEGIN
    DELETE
    FROM Semester WHERE id = semesterId;
END$$

-- Update semester by Id
CREATE PROCEDURE UpdateSemester(IN semesterId INTEGER, IN fromTime DATE, IN toTime DATE)
BEGIN
	  UPDATE Semester
	  SET from_time = fromTime, to_time = toTime
	  WHERE id = semesterId;
END$$

-- -- Create an account ??
CREATE PROCEDURE CreateAccount(IN userName VARCHAR(255), IN userPassword VARCHAR(255), IN firstName VARCHAR(255), IN lastName VARCHAR(255))
BEGIN
    INSERT INTO Account (user_name, user_password, first_name, last_name)
    VALUE (userName, userPassword, firstName, lastName);
END$$


-- Get student account list                                                   OK
CREATE PROCEDURE GetStudents()
BEGIN
    SELECT account_id, code AS student_code, user_name, user_password, first_name, last_name
    FROM Student
    JOIN Account ON Student.account_id = Account.id;
END$$

-- Get student account list                                                   OK
CREATE PROCEDURE GetStudentById(IN studentId INTEGER)
BEGIN
    SELECT account_id, code AS student_code, user_name, user_password, first_name, last_name
    FROM Student
    JOIN Account ON Student.account_id = Account.id;
    WHERE Student.id = studentId;
END$$

-- Get module list for all students in all modules                            OK
CREATE PROCEDURE GetModulesByStudentId(IN studentId INTEGER)
BEGIN
    SELECT Module.name, Module.code
    FROM ModuleList
    JOIN Student ON ModuleList.student_id = Student.id
    JOIN Module ON Module.id = ModuleList.module_id
    WHERE Student.id = studentId;
END$$

-- Get registered exam of a student
CREATE PROCEDURE GetRegistersByStudentId(IN studentId INTEGER)
BEGIN
    SELECT Module.name, Module.code, Exam.date_of_exam
    FROM Student
    JOIN RegisterList ON Student.id = RegisterList.student_id
    JOIN Exam ON RegisterList.exam_id = Exam.id
    JOIN Module ON Exam.module_id = Module.id
    WHERE Student.id = studentId;
END$$


-- Update student code                                                        OK
CREATE PROCEDURE UpdateStudentCode(IN studentId INTEGER, IN studentCode VARCHAR(255))
BEGIN
    UPDATE Student
    SET code = studentCode
    WHERE id = studentId;
END$$

-- Delete a student from the system                                           OK
CREATE PROCEDURE DeleteStudentAccount(IN studentId INTEGER)
BEGIN
    DELETE Account, Student
    FROM Account
    INNER JOIN Student
    ON Account.id = Student.account_id
    WHERE Student.id = studentId;
END$$

-- -- Create a student account                                                          OK
CREATE PROCEDURE CreateStudentAccount(IN userName VARCHAR(255), IN userPassword VARCHAR(255), IN firstName VARCHAR(255), IN lastName VARCHAR(255), IN studentCode VARCHAR(255))
BEGIN
    CALL CreateAccount(userName, userPassword, firstName, lastName);
    INSERT INTO Student (account_id, code)
    VALUE (LAST_INSERT_ID(), studentCode);
END$$

--  Find student account by username and password                         OK
CREATE PROCEDURE VerifyStudentAccount(IN userName VARCHAR(255), IN userPassword VARCHAR(255))
BEGIN
    SELECT Student.*, Account.*
    FROM Student
    JOIN Account ON Student.account_id = Account.id
    WHERE Account.user_name = userName
    AND Account.user_password = userPassword;
END$$

-- View attendance list by session id as Lecturer and Assistant
CREATE PROCEDURE GetAttendanceListBySessionId(IN sessionId INTEGER)
BEGIN
    SELECT Student.*, Account.*
    FROM Student
    JOIN Attendance ON Student.id = Attendance.student_id
    JOIN Account ON Student.account_id = Account.id
    WHERE Attendance.session_id = sessionId;
END$$

--  View an register list by the exam id as Lecturer and Assistant
CREATE PROCEDURE GetRegisterByExamId(IN examId INTEGER)
BEGIN
    SELECT Student.*, Account.*
    FROM Student
    JOIN RegisterList ON Student.account_id = RegisterList.student_id
    JOIN Account ON Student.id = Account.id
    WHERE RegisterList.exam_id = examId;
END$$






-- Get lecturer account list
CREATE PROCEDURE GetLecturers()
BEGIN
    SELECT account_id, user_name, user_password, first_name, last_name
    FROM Lecturer
    JOIN Account ON Lecturer.account_id = Account.id;
END$$

--  View module as Lecturer
CREATE PROCEDURE GetModulesByLecturerId(IN lecturerId INTEGER)
BEGIN
    SELECT *
    FROM Module
    JOIN LecturerModule ON Module.id = LecturerModule.module_id
    WHERE lecturer_id = lecturerId;
END$$

-- -- Create a lecturer account ??
CREATE PROCEDURE CreateLecturerAccount(IN userName VARCHAR(255), IN userPassword VARCHAR(255), IN firstName VARCHAR(255), IN lastName VARCHAR(255))
BEGIN
    CALL CreateAccount(userName, userPassword, firstName, lastName);
    INSERT INTO Lecturer (account_id)
    VALUE (LAST_INSERT_ID());
END$$

-- Delete a lecturer from the system
CREATE PROCEDURE DeleteLecturerAccount(IN lecturerId INTEGER)
BEGIN
    DELETE Account, Lecturer
    FROM Account
    INNER JOIN Lecturer
    ON Account.id = Lecturer.account_id
    WHERE Lecturer.id = lecturerId;
END$$

--  Lecturer login
CREATE PROCEDURE VerifyLecturerAccount(IN userName VARCHAR(255), IN userPassword VARCHAR(255))
BEGIN
    SELECT Lecturer.*, Account.*
    FROM Lecturer
    JOIN Account ON Lecturer.account_id = Account.id
    WHERE Account.user_name = userName
    AND Account.user_password = userPassword;
END$$




-- -- Create an assistant account ??
CREATE PROCEDURE CreateAssistantAccount(IN userName VARCHAR(255), IN userPassword VARCHAR(255), IN firstName VARCHAR(255), IN lastName VARCHAR(255))
BEGIN
    INSERT INTO Account (user_name, user_password, first_name, last_name)
    VALUE (userName, userPassword, firstName, lastName);
    INSERT INTO Assistant (account_id)
    VALUE (LAST_INSERT_ID());
END$$

--  Delete assistant account
CREATE PROCEDURE DeleteAssistantAccount(IN assistantId INTEGER)
BEGIN
    DELETE Account, Assistant
    FROM Account
    INNER JOIN Assistant
    ON Account.id = Assistant.account_id
    WHERE Assistant.id = assistantId;
END$$

--  Assistant login
CREATE PROCEDURE VerifyAssistantAccount(IN userName VARCHAR(255), IN userPassword VARCHAR(255))
BEGIN
    SELECT Assistant.*, Account.*
    FROM Assistant
    JOIN Account ON Assistant.account_id = Account.id
    WHERE Account.user_name = userName
    AND Account.user_password = userPassword;
END$$






--  List all overlap module
CREATE PROCEDURE GetOverlapModules()
BEGIN
    SELECT M1.*, M2.* FROM(
        SELECT S1.module_id AS Module1Id,
            S1.id AS Session1Id,
            S1.date_of_session AS Date1,
            S1.from_time AS Start1,
            S1.to_time AS End1,
            S2.module_id AS Module2Id,
            S2.id AS Session2Id,
            S2.date_of_session AS Date2,
            S2.from_time AS Start2,
            S2.to_time AS End2
        FROM ModuleSession S1, ModuleSession S2
        WHERE S1.id < S2.id AND
            S1.date_of_session = S2.date_of_session AND ((
                S1.from_time < S2.from_time AND S2.from_time < S1.to_time)
                OR (S2.from_time < S1.from_time AND S1.from_time < S2.to_time)
            )
        ) as SS
JOIN Module M1 ON SS.Module1Id = M1.id
JOIN Module M2 ON SS.Module2Id = M2.id;
END$$

--  Student can view the module enrollment list
CREATE PROCEDURE GetEnrollsByModuleId(IN moduleId INTEGER)
BEGIN
    SELECT Student.*, Account.*
    FROM Student
    JOIN ModuleList ON Student.id = ModuleList.student_id
    WHERE ModuleList.module_id = moduleId;
END$$

--  Get all modules
CREATE PROCEDURE GetAllModulesDetails()
BEGIN
    SELECT *
    FROM Module;
END$$

-- View the details of a module
CREATE PROCEDURE GetModuleById(IN moduleId INTEGER)
BEGIN
    SELECT *
    FROM Module
    WHERE id = moduleId;
END$$

-- Create a module
CREATE PROCEDURE CreateModule(IN moduleCode VARCHAR(255), IN moduleName VARCHAR(255), IN semesterId INTEGER)
BEGIN
    INSERT INTO Module(code, name, semester_id)
    VALUE (moduleCode, moduleName, semesterId);
END$$

--  Delete a module
CREATE PROCEDURE DeleteModule(IN moduleId INTEGER)
BEGIN
    DELETE
    FROM Module
    WHERE id = moduleId;
END$$

--  Update an module information
CREATE PROCEDURE UpdateModule(IN moduleId INTEGER, IN moduleCode VARCHAR(20), IN moduleName VARCHAR(100))
BEGIN
    UPDATE Module
    SET code = moduleCode, name = moduleName
    WHERE id = moduleId;
END$$

--  Enroll a student
CREATE PROCEDURE EnrollStudent(IN studentId INTEGER, IN moduleId INTEGER)
BEGIN
    INSERT INTO ModuleList(student_id, module_id)
    VALUE (studentId, moduleId);
END$$

--  Unenrollment for a student
CREATE PROCEDURE UnenrollStudent(IN studentId INTEGER, IN moduleId INTEGER)
BEGIN
    DELETE
    FROM ModuleList
    WHERE student_id = studentId AND module_id = moduleId;
END$$

-- Get module list for all students in all modules
CREATE PROCEDURE GetEnrollmentLists()
BEGIN
    SELECT student_id, first_name, last_name, Module.name, Module.code
    FROM ModuleList
    JOIN Student ON ModuleList.student_id = Student.id
    JOIN Account ON Student.account_id = Account.id
    JOIN Module ON Module.id = ModuleList.module_id;
END$$






-- Create a session
CREATE PROCEDURE CreateModuleSession(IN sessionDate DATE, IN fromTime TIME, IN toTime TIME, IN moduleId INTEGER)
BEGIN
    INSERT INTO ModuleSession(date_of_session, from_time, to_time, module_id)
    VALUE (sessionDate, fromTime, toTime, moduleId);
END$$

-- Delete a session
CREATE PROCEDURE DeleteModuleSession(IN moduleSessionId INTEGER)
BEGIN
    DELETE FROM ModuleSession
    WHERE id = moduleSessionId;
END$$

-- Update a session
CREATE PROCEDURE UpdateModuleSession(IN moduleSessionId INTEGER, IN sessionDate DATE, IN fromTime TIME, IN toTime TIME)
BEGIN
    UPDATE ModuleSession
    SET date_of_session = sessionDate, from_time = fromTime, to_time = toTime
    WHERE id = moduleSessionId;
END$$

-- View the details of a module session
CREATE PROCEDURE GetModuleSessionById(IN sessionId INTEGER)
BEGIN
    SELECT *
    FROM ModuleSession
    WHERE id = sessionId;
END$$

-- View the details of a module session
CREATE PROCEDURE GetModuleSessionByModuleId(IN moduleId INTEGER)
BEGIN
    SELECT *
    FROM ModuleSession
    WHERE module_id = moduleId;
END$$



-- Create exam
CREATE PROCEDURE CreateExam(
  IN dateOfExam    DATE,
  IN fromTime       TIME,
  IN toTime         TIME,
  IN deadline        DATE,
  IN moduleId       INTEGER
)
BEGIN
INSERT INTO Exam(date_of_exam, from_time, to_time, deadline, module_id)
VALUES
    (dateOfExam, fromTime, toTime, deadline, moduleId);
END$$

--  Delete an exam
CREATE PROCEDURE DeleteExam(IN examId INTEGER)
BEGIN
    DELETE
    FROM Exam
    WHERE id = examId;
END$$

--  Update exam date/time
CREATE PROCEDURE UpdateExam(IN examId INTEGER, IN examDate Date, IN examFromTime TIME, IN examToTime TIME, IN DeadLine Date)
BEGIN
    UPDATE Exam
    SET date_of_exam = examDate, from_time = examFromTime, to_time = examToTime, deadline = DeadLine
    WHERE id = examId;
END$$

--  Get all exams
CREATE PROCEDURE GetExams()
BEGIN
    SELECT *
    FROM Exam;
END$$

-- List all the exams for a given semester
CREATE PROCEDURE GetExamById(IN examId INTEGER)
BEGIN
    SELECT *
    FROM Exam
    WHERE id = examId;
END$$

-- List all the exams for a given semester
CREATE PROCEDURE GetExamBySemesterId(IN semesterId INTEGER)
BEGIN
    SELECT date_of_exam, Exam.from_time, Exam.to_time, deadline, code, name, Semester.from_time AS Semester_from_time, Semester.to_time AS Semester_to_time
    FROM Exam JOIN Module ON Exam.module_id = Module.id
    JOIN Semester ON Module.semester_id = Semester.id
    WHERE Semester.id = semesterId;
END$$



-- Register an exam for a student
CREATE PROCEDURE CreateRegister(IN studentId INTEGER, IN examId INTEGER)
BEGIN
    INSERT INTO RegisterList(student_id, exam_id)
    VALUES (studentId, examId);
END$$


-- Unregister
CREATE PROCEDURE DeleteRegister(IN studentId INTEGER, IN examId INTEGER)
BEGIN
    DELETE
    FROM RegisterList
    WHERE student_id = studentId AND exam_id = examId;
END$$

--  Get register list for all students in all modules
CREATE PROCEDURE GetRegisters()
BEGIN
    SELECT student_id, first_name, last_name, Module.name, Module.code, Exam.date_of_exam
    FROM Student
    JOIN RegisterList ON Student.id = RegisterList.student_id
    JOIN Account ON Student.account_id = Account.id
    JOIN Exam ON RegisterList.exam_id = Exam.id
    JOIN Module ON Exam.module_id = Module.id;
END$$




-- Sign the attendance list
CREATE PROCEDURE CreateAttendance(IN studentId INTEGER, IN sessionId INTEGER)
BEGIN
    INSERT INTO Attendance(student_id, session_id)
    VALUES (studentId, sessionId);
END$$


-- Unsign
CREATE PROCEDURE DeleteAttendance(IN studentId INTEGER, IN sessionId INTEGER)
BEGIN
    DELETE
    FROM Attendance
    WHERE student_id = studentId AND session_id = sessionId;
END$$

--Get Lecturer by ID

CREATE PROCEDURE GetLecturerById(IN lectureId INTEGER)
BEGIN
    SELECT Lecturer.*, Account.*
    FROM Lecturer
    JOIN Account ON Lecturer.account_id = Account.id;
    WHERE Lecturer.id = lecturerId;
END$$


--Get Assistant by ID
CREATE PROCEDURE GetAssistantById(IN assistantId INTEGER)
BEGIN
    SELECT Assistant.*, Account.*
    FROM Assistant
    JOIN Account ON Assistant.account_id = Account.id;
    WHERE Assistant.id = assistantId;
END$$

DELIMITER ;