USE register_db;

DELIMITER $$

-- Get student account list                                                   OK
CREATE PROCEDURE GetStudents()
BEGIN
    SELECT account_id, code AS student_code, user_name, user_password, first_name, last_name
    FROM Student
    JOIN Account ON Student.account_id = Account.id;
END$$

-- Get module list for all students in all modules                            OK
CREATE PROCEDURE GetModulesByStudentId(IN studentId INTEGER)
BEGIN
    SELECT Module.name, Module.code
    FROM ModuleList
    JOIN Student ON ModuleList.student_id = Student.id
    JOIN Module ON Module.id = ModuleList.module_id;
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
CREATE PROCEDURE UpdateStudentCodeByStudentId(IN studentId INTEGER, IN studentCode VARCHAR(255))
BEGIN
    UPDATE Student
    SET code = studentCode
    WHERE id = 1;
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
    INSERT INTO Account (user_name, user_password, first_name, last_name)
    VALUE (userName, userPassword, firstName, lastName);
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
    INSERT INTO Account (user_name, user_password, first_name, last_name)
    VALUE (userName, userPassword, firstName, lastName);
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

-- View the details of a module
CREATE PROCEDURE GetModuleDetails(IN moduleId INTEGER)
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














-- Create a session
CREATE PROCEDURE CreateModuleSession(IN sessionDate DATE, IN fromTime TIME, IN toTime TIME, IN moduleId INTEGER)
BEGIN
    INSERT INTO 
END

DELIMITER ;