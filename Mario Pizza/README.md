
## Analysis of given Data

### Things that stood out

* No File for Customers or addresses -> All just part of the order Files.
* Each Product had a boolean if it was either spicy or vegetarian.
* The file with the branches was just a text file with all the addresses of the branches.
* Different kinds of ingredients can just be imported into the new database.

## Analysis of Dominos

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

## Normalisation

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
