set search_path to shopping;

CREATE TABLE UserGroup (
  UserGroupID BIGSERIAL NOT NULL PRIMARY KEY,
  Code VARCHAR(100) NOT NULL,
  Name VARCHAR(255) NOT NULL,
  Description VARCHAR(255),
  CreatedDate TIMESTAMP NOT NULL,
  ModifiedDate TIMESTAMP,
  UNIQUE (Code)
);

CREATE TABLE Permission (
  PermissionID BIGSERIAL NOT NULL PRIMARY KEY,
  Permission VARCHAR(255) NOT NULL,
  Description VARCHAR(255),
  CreatedDate TIMESTAMP NOT NULL,
  ModifiedDate TIMESTAMP,
  UNIQUE(Permission)
);
CREATE TABLE Role (
  RoleID BIGSERIAL NOT NULL PRIMARY KEY,
  Role VARCHAR(100) NOT NULL,
  Description VARCHAR(255),
  CreatedDate TIMESTAMP NOT NULL,
  ModifiedDate TIMESTAMP,
  UNIQUE(Role)
);

CREATE TABLE RoleAcl (
  RoleAclId BIGSERIAL NOT NULL PRIMARY KEY,
  RoleID BIGINT NOT NULL,
  PermissionID BIGINT NOT NULL,
  UNIQUE(RoleID, PermissionID)
);
CREATE INDEX RoleAcl_RoleID_IDx ON RoleAcl USING btree (RoleID);
ALTER TABLE RoleAcl ADD FOREIGN KEY (RoleID) REFERENCES Role (RoleID);
CREATE INDEX RoleAcl_PermisionID_IDx ON RoleAcl USING btree (PermissionID);
ALTER TABLE RoleAcl ADD FOREIGN KEY (PermissionID) REFERENCES Permission (PermissionID);

CREATE TABLE UserGroupAcl (
  UserGroupAclID BIGSERIAL NOT NULL PRIMARY KEY,
  UserGroupID BIGINT NOT NULL,
  PermissionID BIGINT NOT NULL,
  UNIQUE(UserGroupID, PermissionID)
);
CREATE INDEX UserGroupAcl_RoleID_IDx ON UserGroupAcl USING btree (UserGroupID);
ALTER TABLE UserGroupAcl ADD FOREIGN KEY (UserGroupID) REFERENCES UserGroup (UserGroupID);
CREATE INDEX UserGroupAcl_PermisionID_IDx ON UserGroupAcl USING btree (PermissionID);
ALTER TABLE UserGroupAcl ADD FOREIGN KEY (PermissionID) REFERENCES Permission (PermissionID);

CREATE TABLE Users(
  UserID BIGSERIAL NOT NULL PRIMARY KEY,
  Username VARCHAR(255) NOT NULL,
  Password VARCHAR(255) NOT NULL,
  Email VARCHAR(255) NOT NULL,
  FirstName VARCHAR(255) NOT NULL,
  LastName VARCHAR(255) NOT NULL,
  FullName VARCHAR(255),
  UserGroupID BIGINT NOT NULL,
  Status INTEGER NOT NULL,
  CreatedDate TIMESTAMP NOT NULL,
  ModifiedDate TIMESTAMP,
  UNIQUE(Username),
  UNIQUE(Email)
);
CREATE INDEX Users_UserGroupID_IDx ON Users USING btree (UserGroupID);
ALTER TABLE Users ADD FOREIGN KEY (UserGroupID) REFERENCES UserGroup (UserGroupID);

CREATE TABLE UserProfile (
  UserProfileID BIGSERIAL NOT NULL PRIMARY KEY,
  UserID BIGINT NOT NULL,
  Title VARCHAR(10),
  avatar TEXT,
  FirstName VARCHAR(255),
  LastName VARCHAR(255),
  PhoneNumber VARCHAR(20),
  CreatedDate TIMESTAMP NOT NULL,
  ModifiedDate TIMESTAMP,
  CompanyName VARCHAR(255),
  CompanyPhone VARCHAR(20),
  CompanyAddress VARCHAR(255),
  TaxCode VARCHAR(50),
  City VARCHAR(255),
  State VARCHAR(255),
  CountryCode VARCHAR(5),
  UNIQUE(UserId)
);
CREATE INDEX UserProfile_UserID_IDx ON UserProfile USING btree (UserID);
ALTER TABLE UserProfile ADD FOREIGN KEY (UserID) REFERENCES Users(UserID);

CREATE TABLE UserRole (
  UserRoleID BIGSERIAL NOT NULL PRIMARY KEY,
  UserID BIGINT NOT NULL,
  RoleID BIGINT NOT NULL,
  CreatedDate TIMESTAMP NOT NULL,
  ModifiedDate TIMESTAMP,
  UNIQUE(UserID, RoleID)
);
CREATE INDEX UserRole_UserID_IDx ON UserRole USING btree (UserID);
ALTER TABLE UserRole ADD FOREIGN KEY (UserID) REFERENCES Users(UserID);
CREATE INDEX UserRole_RoleID_IDx ON UserRole USING btree (RoleID);
ALTER TABLE UserRole ADD FOREIGN KEY (RoleID) REFERENCES Role(RoleID);