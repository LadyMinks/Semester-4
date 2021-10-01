CREATE TABLE OrderStatus
(
    id   int IDENTITY PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE Address
(
    Id          int IDENTITY PRIMARY KEY,
    Address     varchar(255) NOT NULL,
    Postal_Code varchar(255)NOT NULL,
    City        varchar(255)NOT NULL
);

CREATE TABLE Customer
(
    Id       int PRIMARY KEY,
    Name     varchar(255)NOT NULL,
    Email    varchar(255) NOT NULL,
    password varchar(255)NOT NULL
);

CREATE TABLE CustomerAddress
(
    Id          int IDENTITY PRIMARY KEY,
    Customer_id int FOREIGN KEY REFERENCES customer (Id),
    Address_id  int FOREIGN KEY REFERENCES address (Id)
);

CREATE TABLE Branch
(
    Id         int IDENTITY PRIMARY KEY,
    Name       varchar(255) NOT NULL,
    address_id int NOT NULL FOREIGN KEY REFERENCES address (Id)
);

CREATE TABLE VatCodes
(
    Id         int IDENTITY PRIMARY KEY NOT NULL,
    Percentage float NOT NULL
);

CREATE TABLE MarioOrder
(
    Id           int IDENTITY PRIMARY KEY,
    Branch_Id    int NOT NULL FOREIGN KEY REFERENCES branch (Id),
    Address_Id   int NOT NULL FOREIGN KEY REFERENCES address (Id),
    Customer_Id  int NOT NULL FOREIGN KEY REFERENCES customer (Id),
    Status_Id    int NOT NULL FOREIGN KEY REFERENCES OrderStatus (Id),
    Vat_Code_Id  int NOT NULL FOREIGN KEY REFERENCES VatCodes (Id),
    Name         varchar(255) NOT NULL,
    Phone        varchar(255) NOT NULL,
    Email        varchar(255) NOT NULL,
    Notes        varchar(max) NOT NULL,
    Placed_At    DATETIME2 NOT NULL DEFAULT GETDATE(),
    Delivered_At DATETIME2
);

CREATE TABLE ProductType
(
    Id int IDENTITY PRIMARY KEY ,
    Name nvarchar(255) NOT NULL
)

CREATE TABLE Category
(
    Id int IDENTITY PRIMARY KEY,
    ParentId int FOREIGN KEY REFERENCES Category(Id),
    Name nvarchar(255) NOT NULL
)

CREATE TABLE OptionList
(
    Id int IDENTITY PRIMARY KEY,
    ProductTypeId int NOT NULL FOREIGN KEY REFERENCES ProductType(Id),
    Name nvarchar(255) NOT NULL
)

CREATE TABLE Options
(
    Id int IDENTITY PRIMARY KEY ,
    OptionListId int NOT NULL FOREIGN KEY REFERENCES OptionList(Id),
    Name nvarchar(255) NOT NULL,
    Description nvarchar(max),
    Price decimal(8,3) NOT NULL
)

CREATE TABLE ProductOption
(
    ProductId int
)


