drop table primary_117;
CREATE TABLE Persons(
P_Id numeric(1) NOT NULL PRIMARY KEY,
LastName varchar(255) NOT NULL,
FirstName varchar(255),
Address varchar(255),
City varchar(255)
)

insert into primary_117 (1,3);
insert into primary_117 (2,4);
insert into primary_117 (3,5);

select * from primary_117;
update primary_117 set c1=7 where c2=4;
select * from primary_117;
