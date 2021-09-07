create table customer_address
(
	Id int identity
		primary key,
	Customer_id int
		references customer,
	Address_id int
		references address
);

