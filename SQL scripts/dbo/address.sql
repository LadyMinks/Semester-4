use dominospizzas;

create table address
(
	Id int identity
		primary key,
	Address varchar(255),
	Postal_Code varchar(255),
	City varchar(255)
);

