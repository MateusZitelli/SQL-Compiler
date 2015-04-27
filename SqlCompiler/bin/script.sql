use primary_117;
CREATE TABLE Persons(
P_Id numeric(1) NOT NULL PRIMARY KEY,
LastName varchar(255) NOT NULL,
FirstName varchar(255),
Address varchar(255),
City varchar(255)
)

insert into Persons (P_id, LastName) values (1, "wow");

select * from Persons;
