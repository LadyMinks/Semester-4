DROP TABLE if exists customer_address;
DROP TABLE if exists mario_order;
DROP TABLE if exists address;
DROP TABLE if exists branch;
DROP TABLE if exists customer;
DROP TABLE if exists order_status;
DROP TABLE if exists vat_codes;

CREATE TABLE ProductType
(
    Id int IDENTITY PRIMARY KEY,
    Name varchar(50) NOT NULL
);

CREATE TABLE Category
(
    Id int IDENTITY PRIMARY KEY,
    ParentId int,
    Name varchar(50) NOT NULL,
    CONSTRAINT FK_Category_Category FOREIGN KEY (ParentId)
        REFERENCES Category(Id)
);

CREATE TABLE OptionList
(
    Id int IDENTITY PRIMARY KEY,
    ProductTypeId int NOT NULL,
    Name varchar(50) NOT NULL,
    CONSTRAINT FK_OptionList_ProductType FOREIGN KEY (ProductTypeId)
        REFERENCES ProductType(Id)
);

CREATE TABLE [Option]
(
    Id int IDENTITY PRIMARY KEY,
    OptionListId int NOT NULL,
    Name varchar(50) NOT NULL,
    Description nvarchar(max),
    Price decimal(7,2) NOT NULL,
    CONSTRAINT FK_Option_OptionList FOREIGN KEY (OptionListId)
        REFERENCES OptionList(Id)
);

CREATE TABLE VatCode
(
    Id int IDENTITY PRIMARY KEY,
    Percentage real NOT NULL
);

CREATE TABLE Product
(
    Id int IDENTITY PRIMARY KEY,
    ProductTypeId int,
    VatCodeId int NOT NULL,
    Code varchar(20) NOT NULL UNIQUE,
    Name nvarchar(100) NOT NULL,
    Description nvarchar(max),
    Price decimal(7,2) NOT NULL,
    CONSTRAINT FK_Product_ProductType FOREIGN KEY (ProductTypeId)
        REFERENCES ProductType(Id),
    CONSTRAINT FK_Product_VatCode FOREIGN KEY (VatCodeId)
        REFERENCES VatCode(Id)
);

CREATE TABLE Ingredient
(
    Id int IDENTITY PRIMARY KEY,
    Name varchar(50) NOT NULL,
    Price decimal(7,2) NOT NULL,
    CONSTRAINT UC_Ingredient_Name_Price UNIQUE (Name, Price)
);

CREATE TABLE ProductIngredient
(
    ProductId int NOT NULL,
    IngredientId int NOT NULL,
    Quantity tinyint NOT NULL,
    CONSTRAINT PK_ProductIngredient_Product_Ingredient PRIMARY KEY (ProductId, IngredientId),
    CONSTRAINT FK_ProductIngredient_Product FOREIGN KEY (ProductId)
        REFERENCES Product(Id)
        ON DELETE CASCADE,
    CONSTRAINT FK_ProductIngredient_Ingredient FOREIGN KEY (IngredientId)
        REFERENCES Ingredient(Id)
        ON DELETE CASCADE
);

CREATE TABLE IngredientProductType
(
    IngredientId int NOT NULL,
    ProductTypeId int NOT NULL,
    CONSTRAINT PK_IngredientProductType_Ingredient_ProductType PRIMARY KEY (IngredientId, ProductTypeId),
    CONSTRAINT FK_IngredientProductType_Ingredient FOREIGN KEY (IngredientId)
        REFERENCES Ingredient(Id)
        ON DELETE CASCADE,
    CONSTRAINT FK_IngredientProductType_ProductType FOREIGN KEY (ProductTypeId)
        REFERENCES ProductType(Id)
        ON DELETE CASCADE
);

CREATE TABLE ProductOption
(
    ProductId int NOT NULL,
    OptionId int NOT NULL,
    CONSTRAINT PK_ProductOption_Product_Option PRIMARY KEY (ProductId, OptionId),
    CONSTRAINT FK_ProductOption_Product FOREIGN KEY (ProductId)
        REFERENCES Product(Id)
        ON DELETE CASCADE,
    CONSTRAINT FK_ProductOption_Option FOREIGN KEY (OptionId)
        REFERENCES [Option](Id)
        ON DELETE CASCADE
);

CREATE TABLE ProductCategory
(
    ProductId int NOT NULL,
    CategoryId int NOT NULL,
    CONSTRAINT PK_ProductCategory_Product_Category PRIMARY KEY (ProductId, CategoryId),
    CONSTRAINT FK_ProductCategory_Product FOREIGN KEY (ProductId)
        REFERENCES Product(Id)
        ON DELETE CASCADE,
    CONSTRAINT FK_ProductCategory_Category FOREIGN KEY (CategoryId)
        REFERENCES Category(Id)
        ON DELETE CASCADE
);

CREATE TABLE Property
(
    Id int IDENTITY PRIMARY KEY,
    Name varchar NOT NULL
);

CREATE TABLE ProductProperty
(
    ProductId int NOT NULL,
    PropertyId int NOT NULL,
    CONSTRAINT PK_ProductProperty_Product_Property PRIMARY KEY (ProductId, PropertyId),
    CONSTRAINT FK_ProductProperty_Product FOREIGN KEY (ProductId)
        REFERENCES Product(Id)
        ON DELETE CASCADE,
    CONSTRAINT FK_ProductProperty_Property FOREIGN KEY (PropertyId)
        REFERENCES Property(Id)
        ON DELETE CASCADE
);

CREATE TABLE Address
(
    Id int IDENTITY PRIMARY KEY,
    Address varchar(50) NOT NULL,
    PostalCode varchar(8) NOT NULL,
    City varchar(50) NOT NULL
);

CREATE TABLE Customer
(
    Id int IDENTITY PRIMARY KEY,
    Name nvarchar(50) NOT NULL,
    Email varchar(60) NOT NULL UNIQUE,
    Password char(60) NOT NULL,
    Phone varchar(20) NOT NULL
);

CREATE TABLE CustomerAddress
(
    CustomerId int NOT NULL,
    AddressId int NOT NULL,
    CONSTRAINT PK_CustomerAddress_Customer_Address PRIMARY KEY (CustomerId, AddressId),
    CONSTRAINT FK_CustomerAddress_Customer FOREIGN KEY (CustomerId)
        REFERENCES Customer(Id)
        ON DELETE CASCADE,
    CONSTRAINT FK_CustomerAddress_Address FOREIGN KEY (AddressId)
        REFERENCES Address(Id)
        ON DELETE CASCADE
);

CREATE TABLE Status
(
    Id int IDENTITY PRIMARY KEY,
    Name varchar(30) NOT NULL
);

CREATE TABLE Branch
(
    Id int IDENTITY PRIMARY KEY,
    Name varchar(50) NOT NULL,
    AddressId int NOT NULL,
    CONSTRAINT FK_Branch_Address FOREIGN KEY (AddressId)
        REFERENCES Address(Id)
);

CREATE TABLE Street
(
    Id int IDENTITY PRIMARY KEY,
    Name varchar(50) NOT NULL UNIQUE
);

CREATE TABLE City
(
    Id int IDENTITY PRIMARY KEY,
    Name varchar(50) NOT NULL UNIQUE
);

CREATE TABLE PostalCode
(
    PostalCode varchar(6) NOT NULL,
    NumberFrom int NOT NULL,
    NumberTo int NOT NULL,
    StreetId int NOT NULL,
    CityId int NOT NULL,
    BranchId int,
    CONSTRAINT PK_PostalCode_PostalCode_NumberFrom_NumberTo PRIMARY KEY (PostalCode, NumberFrom, NumberTo),
    CONSTRAINT FK_PostalCode_Street FOREIGN KEY (StreetId)
        REFERENCES Street(Id),
    CONSTRAINT FK_PostalCode_City FOREIGN KEY (CityId)
        REFERENCES City(Id),
    CONSTRAINT FK_PostalCode_Branch FOREIGN KEY (BranchId)
        REFERENCES Branch(Id)
);

CREATE INDEX IX_PostalCode_PostalCode ON PostalCode(PostalCode);

CREATE TABLE [Order]
(
    Id int IDENTITY PRIMARY KEY,
    BranchId int NOT NULL,
    AddressId int NOT NULL,
    CustomerId int NOT NULL,
    StatusId int NOT NULL,
    Name nvarchar(50) NOT NULL,
    Phone varchar(20) NOT NULL,
    Email varchar(60) NOT NULL,
    Notes nvarchar(300) NOT NULL,
    PlacedAt datetime2 NOT NULL DEFAULT GETDATE(),
    DeliverAt datetime2,
    CONSTRAINT FK_Order_Branch FOREIGN KEY (BranchId)
        REFERENCES Branch(Id),
    CONSTRAINT FK_Order_Address FOREIGN KEY (AddressId)
        REFERENCES Address(Id),
    CONSTRAINT FK_Order_Customer FOREIGN KEY (CustomerId)
        REFERENCES Customer(Id),
    CONSTRAINT FK_Order_Status FOREIGN KEY (StatusId)
        REFERENCES Status(Id)
);

CREATE TABLE OrderLine
(
    Id int IDENTITY PRIMARY KEY,
    OrderId int NOT NULL,
    ParentId int,
    ProductId int,
    IngredientId int,
    OptionId int,
    VatCodeId int NOT NULL,
    Name nvarchar(50) NOT NULL,
    Price decimal(7,2) NOT NULL,
    Quantity smallint NOT NULL,
    CONSTRAINT FK_OrderLine_Order FOREIGN KEY (OrderId)
        REFERENCES [Order](Id),
    CONSTRAINT FK_OrderLine_OrderLine FOREIGN KEY (ParentId)
        REFERENCES OrderLine(Id),
    CONSTRAINT FK_OrderLine_Product FOREIGN KEY (ProductId)
        REFERENCES Product(Id),
    CONSTRAINT FK_OrderLine_Ingredient FOREIGN KEY (IngredientId)
        REFERENCES Ingredient(Id),
    CONSTRAINT FK_OrderLine_Option FOREIGN KEY (OptionId)
        REFERENCES [Option](Id),
    CONSTRAINT FK_OrderLine_VatCode FOREIGN KEY (VatCodeId)
        REFERENCES VatCode(Id)
);

CREATE TABLE CouponType
(
    Id int IDENTITY PRIMARY KEY,
    Name varchar(30) NOT NULL
);

CREATE TABLE Coupon
(
    Id int IDENTITY PRIMARY KEY,
    CouponTypeId int NOT NULL,
    Code varchar(30) NOT NULL UNIQUE,
    Name varchar(30) NOT NULL,
    Percentage real,
    FixedPrice decimal(7,2),
    ExpiresAt datetime2,
    Reusable bit NOT NULL,
    Active bit NOT NULL,
    CONSTRAINT FK_Coupon_CouponType FOREIGN KEY (CouponTypeId)
        REFERENCES CouponType(Id)
);

CREATE TABLE OrderCoupon
(
    OrderId int NOT NULL,
    CouponId int NOT NULL,
    CONSTRAINT PK_OrderCoupon_Order_Coupon PRIMARY KEY (OrderId, CouponId),
    CONSTRAINT FK_OrderCoupon_Order FOREIGN KEY (OrderId)
        REFERENCES [Order](Id)
        ON DELETE CASCADE,
    CONSTRAINT FK_OrderCoupon_Coupon FOREIGN KEY (CouponId)
        REFERENCES Coupon(Id)
        ON DELETE CASCADE
);