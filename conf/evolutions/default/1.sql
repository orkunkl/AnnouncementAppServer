
# Database schema

# --- !Ups

CREATE TABLE Websites
(
  websiteID SERIAL PRIMARY KEY,
  link character varying,
  parseFormat character varying
);

CREATE TABLE Groups
(
  groupID SERIAL PRIMARY KEY,
  groupToken character varying,
  websiteID integer REFERENCES Websites (websiteID) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE Users
(
  userID SERIAL PRIMARY KEY,
  userToken character varying
);
CREATE TABLE UserGroupRelation
(
  ID SERIAL PRIMARY KEY,
  userID integer REFERENCES Users (userID) ON UPDATE NO ACTION ON DELETE CASCADE,
  groupID integer REFERENCES Groups (groupID) ON UPDATE NO ACTION ON DELETE CASCADE
);
# --- !Downs

DROP TABLE Users;
DROP TABLE Groups;
DROP TABLE Websites;
DROP TABLE UserGroupRelation;
