USE register_db;

SELECT Student.*, Account.*
    FROM Student
    JOIN Account ON Student.account_id = Account.id
    WHERE Student.id = studentId;
/*
DELIMITER $$

CREATE PROCEDURE GetStudentById(IN studentId INTEGER)
BEGIN

END$$

DELIMITER ;*/