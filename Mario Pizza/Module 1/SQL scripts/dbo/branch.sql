create table branch
(
	Id int identity
		primary key,
	Name varchar(255),
	address_id int
		references address
);

