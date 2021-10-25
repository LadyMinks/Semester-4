# Beargumentatie Relationele Database Mario Pizza

### Relational database

We decided to go for a relational database. This makes it a lot easier to understand the relationships among all the
available data and enables us to retrieve data in one or more tables with just one single query. But the main reason we
choose to go for a relational database instead of say a noSql database, is the ability to create meaningful information
by joining the tables. Since these tables are interlinked, it makes updating these records a lot easier, thus
maintaining uniform data in all of them. Data in relational databases are also normalized. This process ensures that
there are no duplicate records anywhere.

### MSSQL
We decided to use a MSSQl server. There was some discussion about maybe using MySQL. But I decided to do some research. There are a lot of similarities between the two. For example, Both MySql and MsSql have extensive performance and scaling capabilities. However after some testing involving high volumes of SELECT, INSERT, UPDATE and DELETE queries, it seems that SQL server consistently beat MySQL. 

I personally preferred the SQL server tool bench, SSMS, compared to the MySQL Workbench, mostly because it seems more secure.  The features the SSMS are also more beginner-friendly, while also applicable for experienced Data Base Engineers. 

We also knew that we would be programming in C#, so SQL Server will have a home-court advantage. Microsoft wrote the Entity Framework Core, which facilitates access to databases and data services, specifically for SQL Server. 

### Approach

We started by looking at what shape the database should take. We did this mostly by looking at Dominos Pizza for inspiration. Based on this we made an ERD model. After a few versions, we decided on [this](https://github.com/wickex/sem4-portfolio/blob/main/1-Dominos/erd-3.png) model. We also looked at the given data, and started figuring out how this data would fit into the new model we made. 

We wrote our database script in Datagrip. Datagrip is a very beginner-friendly tools by the makes of JetBrains, designed to query, create and manage databases. It runs on Windows, Linux and MacOS, and has a wide range of DBMS support. When the database was created, we decided for a code-first approach. While a database-first approach, using stored procedures might be more performance efficient, through modern ORMs, this is no longer really an issue. 

We also didn't want to be limited to the logic available to us through an SQL language. With a code-first approach, we have all the logic available to us in the code, and it would be a lot easier to do modifications in there as well.   

#### **C#**

We decided to write our application in C#. It is a mature language, so there will be a lot of documentation available online, and it has a lot of fitting features for out project. C#'s ORM (Entity Framework Core) was created by Microsoft as well as MSSQL, this would make it easier to connect the two. 

### Business Questions


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



   
