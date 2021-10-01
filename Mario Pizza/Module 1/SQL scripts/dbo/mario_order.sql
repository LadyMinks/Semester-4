create table mario_order
(
	Id int identity
		primary key,
	Branch_Id int
		references branch,
	Address_Id int
		references address,
	Customer_Id int
		references customer,
	Status_Id int
		references order_status,
	Vat_Code_Id int
		references vat_codes,
	Name varchar(255),
	Phone varchar(255),
	Email varchar(255),
	Notes varchar(max),
	Placed_At datetime2,
	Delivered_At datetime2
);

