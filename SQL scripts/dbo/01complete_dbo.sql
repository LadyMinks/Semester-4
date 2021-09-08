CREATE TABLE order_status
(
    id   int IDENTITY PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE address(
                   Id int IDENTITY PRIMARY KEY,
                   Address varchar(255),
                   Postal_Code varchar(255),
                   City varchar(255)
               );

CREATE TABLE customer (
                   Id int PRIMARY KEY,
                   Name varchar(255),
                   Email varchar(255),
                   password varchar(255)
               );

CREATE TABLE customer_address (
                   Id int IDENTITY PRIMARY KEY,
                   Customer_id int FOREIGN KEY REFERENCES customer(Id),
                   Address_id int FOREIGN KEY REFERENCES address(Id)
               );

CREATE TABLE branch (
                   Id int IDENTITY PRIMARY KEY,
                   Name varchar(255),
                   address_id int FOREIGN KEY REFERENCES address(Id)
               );

CREATE TABLE vat_codes (
                   Id int IDENTITY PRIMARY KEY,
                   Percentage float
               );

CREATE TABLE mario_order
               (
                   Id           int IDENTITY PRIMARY KEY,
                   Branch_Id    int FOREIGN KEY REFERENCES branch (Id),
                   Address_Id   int FOREIGN KEY REFERENCES address (Id),
                   Customer_Id  int FOREIGN KEY REFERENCES customer (Id),
                   Status_Id    int FOREIGN KEY REFERENCES order_status (Id),
                   Vat_Code_Id  int FOREIGN KEY REFERENCES vat_codes (Id),
                   Name         varchar(255),
                   Phone        varchar(255),
                   Email        varchar(255),
                   Notes        varchar(max),
                   Placed_At    DATETIME2,
                   Delivered_At DATETIME2
               );


