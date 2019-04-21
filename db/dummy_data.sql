set search_path to shopping;
INSERT INTO UserGroup(Code, Name, CreatedDate) VALUES('SYSTEM_ADMIN', 'System Administrator', NOW());
INSERT INTO Users(Username, Email, Password, FirstName, LastName, Status, CreatedDate, UserGroupID)
SELECT 'sysadmin', 'sysadmin@sdx.com', '$2a$10$r7bnsEesBPcM88qQbfShPeKPXqMJDm1M3GCSOnyIcP9BLWVmVtXpW', 'tikuka', 'tikuka', 1, NOW(), UserGroupID
FROM UserGroup WHERE Code = 'SYSTEM_ADMIN';