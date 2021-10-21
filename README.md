# Beargumentatie Relationele Database Mario Pizza

### Relational database:

We decided to go for a relational database. This makes it a lot easier to understand the relationships among all the
available data and enables us to retrieve data in one or more tables with just one single query. But the main reason we
choose to go for a relational database instead of say a noSql database, is the ability to create meaningful information
by joining the tables. Since these tables are interlinked, it makes updating these records a lot easier, thus
maintaining uniform data in all of them. Data in relational databases are also normalized. This process ensures that
there are no duplicate records anywhere.

### MSSQL
We decided to use a MSSQl server. There was some discussion about maybe using MySQL. But I decided to do some research. There are a lot of similarities between the two. For example, Both MySql and MsSql have extensive performance and scaling capabilities. However after some testing involving high volumes of SELECT, INSERT, UPDATE and DELETE queries, it seems that SQL server consistently beat MySQL. 

I personally preferred the SQL server tool bench, SSMS, compared to the MySQL Workbench, mostly because it seems more secure.  The features the SSMS are also more beginner-friendly, while also applicable for experienced Data Base Engineers. Being compatible not only with Windows, but recently also with Linux and docker Platforms, makes it a more    

We also knew that we would be programming in C#, so SQL Server will have a home-court advantage. Microsoft wrote the ADO.NET library, which facilitates access to databases and data services, specifically for SQL Server. 


### Approach

We decided to take a code first approach, as that would play to the best of our abilities. Keeping to the ETL process of extracting, transforming and loading the data, we made a C# application. Each File that 
That takes the data by Parsing the old files

### C#



### Power BI

For our visualisation tool we decided to use PowerBI. We were struggling to decide between PowerBI, Looker and Tableau.

Here is a list of the pros and cons for each program.

#### Looker

Pros
* simple UI
* Unique form of visualisation

Cons
* Has it's own language; LookML.
* Takes a lot of effort to set up.
* Needs a lot of Maintenance.
* Costs 3000 euro

#### Tableau

Pros
* Easy to use
* There is a free version
* Multiple connections available. 

Cons
* Official version is very expensive.
* Security problems.
* very resource intensive.

#### PowerBI

Pros

* Can use almost any form of data.
* there is a ffree version.
* Can be both instalational or connected to the cloud.
* Easy to use
* Looks very professional.

Cons
* With the free version it's impossible to share graphs with each other. 

Our Business Questions:



   
## Analysis of given Data

### Things that stood out

* No File for Customers or addresses -> All just part of the order Files.
* Each Product had a boolean if it was either spicy or vegetarian.
* The file with the branches was just a text file with all the addresses of the branches.
* Different kinds of ingredients can just be imported into the new database.

## Analyse van Dominos

Different types of foods/dishes:

- Crunchy Chicken
- Side dishes
- Deserts
- Drinks
- Breads

  And their main dish: Pizza (or sandwiches) with various customization option available:
  - Sizes
  - Sauce
  - Crust
  - Ingredients


- Orders

  Consists of different aspects such as:
  - Branch where ordered
  - The Customer + phone number & email
  - Take away or delivery, +if Delivery Address
  - Whatever that has been ordered + product info
  - Payment method and price
  - Any notes for the deliverer
  - Delivery time


- Customers

  We ran into a problem when we tried to just imagine a Customer with one Address, but Since A customer should be able
  to order deliveries to different addresses, we tried to look at it this way:

## Normalisatie

Normalisation is a guideline to avoid data redundancy and avoid dataloss. If we take a look the orders work, we can
apply some normalisation to simplify this process. An Order exists of multiple parts:

1NF  
<ins>Order ID</ins> PK  
Branch  
Address  
Customer  
Phone number  
Email address  
Notes  
Order Placed At  
Order Delivered At  
Order line    
Coupons

2NF

<ins>ID</ins> PK  
BranchId FK  
AddressId FK  
CustomerId FK  
Phone  
Email   
Notes  
PlacedAt  
DeliveredAt  
OrderLine    
Coupons
