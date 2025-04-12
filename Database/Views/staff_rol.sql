-- Preview Consult
SELECT 
    mr.ROLE_NAME,
    ms.ROLE_ID,
    ms.STAFF_ID,
    CONCAT(ms.FIRST_NAME, ' ' ,ms.LAST_NAME) as STAFF_NAME,
    ms.EMAIL
FROM 
    MEDICAL_STAFF ms 
JOIN 
    MEDICAL_ROL mr ON mr.ROLE_ID = ms.ROLE_ID

-- Create View
-- View to show all medical staff
CREATE VIEW STAFF_ROL AS
SELECT 
    mr.ROLE_NAME,
    ms.ROLE_ID,
    ms.STAFF_ID,
    CONCAT(ms.FIRST_NAME, ' ' ,ms.LAST_NAME) as STAFF_NAME,
    ms.EMAIL
FROM 
    MEDICAL_STAFF ms 
JOIN 
    MEDICAL_ROL mr ON mr.ROLE_ID = ms.ROLE_ID

-- Call View
SELECT * FROM STAFF_ROL