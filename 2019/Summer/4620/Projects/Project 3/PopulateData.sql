INSERT INTO TOPPINGS 
VALUES ('Pepperoni', 1.25, 0.2, 100, 2, 2.75, 3.5, 4.5);
INSERT INTO TOPPINGS
VALUES ('Sausage', 1.25, 0.15, 100, 2.5, 3, 3.5, 4.25);
INSERT INTO TOPPINGS
VALUES ('Ham', 1.5, 0.15, 78, 2, 2.5, 3.25, 4);
INSERT INTO TOPPINGS
VALUES ('Chicken', 1.75, 0.25, 56, 1.5, 2, 2.25, 3);
INSERT INTO TOPPINGS
VALUES ('Green Pepper', 0.5, 0.02, 79, 1, 1.5, 2, 2.5);
INSERT INTO TOPPINGS
VALUES ('Onion', 0.5, 0.02, 85, 1, 1.5, 2, 2.75);
INSERT INTO TOPPINGS
VALUES ('Roma Tomatoes', 0.75, 0.03, 86, 2, 3, 3.5, 4.5);
INSERT INTO TOPPINGS
VALUES ('Mushrooms', 0.75, 0.1, 52, 1.5, 2, 2.5, 3);
INSERT INTO TOPPINGS
VALUES ('Black Olives', 0.6, 0.1, 39, 0.75, 1, 1.5, 2);
INSERT INTO TOPPINGS
VALUES ('Pineapple', 1, 0.25, 15, 1, 1.25, 1.75, 2);
INSERT INTO TOPPINGS
VALUES ('Jalapenos', 0.5, 0.05, 64, 0.5, 0.75, 1.25, 1.75);
INSERT INTO TOPPINGS
VALUES ('Banana Peppers', 0.5, 0.05, 36, 0.6, 1, 1.3, 1.75);
INSERT INTO TOPPINGS
VALUES ('Regular Cheese', 1.5, 0.12, 250, 2, 3.5, 5, 7);
INSERT INTO TOPPINGS
VALUES ('Four Cheese Blend', 2, 0.15, 150, 2, 3.5, 5, 7);
INSERT INTO TOPPINGS
VALUES ('Feta Cheese', 2, 0.18, 75, 1.75, 3, 4, 5.5);
INSERT INTO TOPPINGS
VALUES ('Goat Cheese', 2, 0.2, 54, 1.6, 2.75, 4, 5.5);
INSERT INTO TOPPINGS
VALUES ('Bacon', 1.5, 0.25, 89, 1, 1.5, 2, 3);

INSERT INTO DISCOUNTS
(DISCOUNT_ID, NAME, PERCENT_OFF)
VALUES (01, 'Employee', 15);
INSERT INTO DISCOUNTS
(DISCOUNT_ID, NAME, DOLLAR_OFF)
VALUES (02, 'Lunch Special Medium', 1);
INSERT INTO DISCOUNTS
(DISCOUNT_ID, NAME, DOLLAR_OFF)
VALUES (03, 'Lunch Special Large', 2);
INSERT INTO DISCOUNTS
(DISCOUNT_ID, NAME, DOLLAR_OFF)
VALUES (04, 'Specialty Pizza', 1.5);
INSERT INTO DISCOUNTS
(DISCOUNT_ID, NAME, PERCENT_OFF)
VALUES (05, 'Gameday Special', 20);

INSERT INTO BASE_PRICE
VALUES (01, 'Small', 'Thin', 3, 0.5);
INSERT INTO BASE_PRICE
VALUES (02, 'Small', 'Original', 3, 0.75);
INSERT INTO BASE_PRICE
VALUES (03, 'Small', 'Pan', 3.5, 1);
INSERT INTO BASE_PRICE
VALUES (04, 'Small', 'Gluten-Free', 4, 2);
INSERT INTO BASE_PRICE
VALUES (05, 'Medium', 'Thin', 5, 1);
INSERT INTO BASE_PRICE
VALUES (06, 'Medium', 'Original', 5, 1.5);
INSERT INTO BASE_PRICE
VALUES (07, 'Medium', 'Pan', 6, 2.25);
INSERT INTO BASE_PRICE
VALUES (08, 'Medium', 'Gluten-Free', 6.25, 3);
INSERT INTO BASE_PRICE
VALUES (09, 'Large', 'Thin', 8, 1.25);
INSERT INTO BASE_PRICE
VALUES (10, 'Large', 'Original', 8, 2);
INSERT INTO BASE_PRICE
VALUES (11, 'Large', 'Pan', 9, 3);
INSERT INTO BASE_PRICE
VALUES (12, 'Large', 'Gluten-Free', 9.5, 4);
INSERT INTO BASE_PRICE
VALUES (13, 'X-Large', 'Thin', 10, 2);
INSERT INTO BASE_PRICE
VALUES (14, 'X-Large', 'Original', 10, 3);
INSERT INTO BASE_PRICE
VALUES (15, 'X-Large', 'Pan', 11.5, 4.5);
INSERT INTO BASE_PRICE
VALUES (16, 'X-Large', 'Gluten-Free', 12.5, 6);


-- ORDER #1
INSERT INTO ORDERS
VALUES (001);

INSERT INTO PIZZA
VALUES (001, 'COMPLETE', '2019-03-05 12:03', 9, 001, '13.50', '3.68');

INSERT INTO HAS_TOPPINGS
VALUES (001, 'Regular Cheese', 'Yes'); 
INSERT INTO HAS_TOPPINGS
VALUES (001, 'Pepperoni', 'No'); 
INSERT INTO HAS_TOPPINGS
VALUES (001, 'Sausage', 'No'); 

INSERT INTO PIZZA_DISCOUNTS
VALUES (001, 3);

INSERT INTO DINE_IN
VALUES (001, 14);

INSERT INTO SEAT_NUMBER
VALUES(001, 1);

INSERT INTO SEAT_NUMBER
VALUES(001, 2);

INSERT INTO SEAT_NUMBER
VALUES(001, 3);


-- ORDER #2
INSERT INTO ORDERS
VALUES (002);

INSERT INTO PIZZA
VALUES (002, 'COMPLETE', '2019-03-03 12:05', 7, 002, '10.60', '3.23');

INSERT INTO HAS_TOPPINGS
VALUES (002, 'Feta Cheese', 'NO');
INSERT INTO HAS_TOPPINGS
VALUES (002, 'Black Olives', 'NO');
INSERT INTO HAS_TOPPINGS
VALUES (002, 'Roma Tomatoes', 'NO');
INSERT INTO HAS_TOPPINGS
VALUES (002, 'Mushrooms', 'NO');
INSERT INTO HAS_TOPPINGS
VALUES (002, 'Banana Peppers', 'NO');

INSERT INTO PIZZA_DISCOUNTS
VALUES (002, 2);
INSERT INTO PIZZA_DISCOUNTS
VALUES (002, 4);

INSERT INTO DINE_IN
VALUES (002, 4);

INSERT INTO SEAT_NUMBER
VALUES (002, 1);


-- ORDER #3
INSERT INTO ORDERS
VALUES (003);

INSERT INTO PIZZA
VALUES (003, 'COMPLETE', '2019-03-03 12:05', 2, 003, '6.75', '1.40');

INSERT INTO HAS_TOPPINGS
VALUES (003, 'Regular Cheese', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (003, 'Chicken', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (003, 'Banana Peppers', 'No');

INSERT INTO DINE_IN
VALUES (003, 4);

INSERT INTO SEAT_NUMBER
VALUES (003, 2);


-- ORDER #4
INSERT INTO ORDERS
VALUES (004);

INSERT INTO CUSTOMER
VALUES (1, 'Andrew Wilkes-Krier', NULL, '864-254-5861');

INSERT INTO PIZZA
VALUES (004, 'COMPLETE', '2019-03-03 9:30', 10, 004, '10.75', '3.30');
INSERT INTO PIZZA
VALUES (005, 'COMPLETE', '2019-03-03 9:30', 10, 004, '10.75', '3.30');
INSERT INTO PIZZA
VALUES (006, 'COMPLETE', '2019-03-03 9:30', 10, 004, '10.75', '3.30');
INSERT INTO PIZZA
VALUES (007, 'COMPLETE', '2019-03-03 9:30', 10, 004, '10.75', '3.30');
INSERT INTO PIZZA
VALUES (008, 'COMPLETE', '2019-03-03 9:30', 10, 004, '10.75', '3.30');
INSERT INTO PIZZA
VALUES (009, 'COMPLETE', '2019-03-03 9:30', 10, 004, '10.75', '3.30');

INSERT INTO HAS_TOPPINGS
VALUES (004, 'Regular Cheese', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (004, 'Pepperoni', 'No');

INSERT INTO HAS_TOPPINGS
VALUES (005, 'Regular Cheese', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (005, 'Pepperoni', 'No');

INSERT INTO HAS_TOPPINGS
VALUES (006, 'Regular Cheese', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (006, 'Pepperoni', 'No');

INSERT INTO HAS_TOPPINGS
VALUES (007, 'Regular Cheese', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (007, 'Pepperoni', 'No');

INSERT INTO HAS_TOPPINGS
VALUES (008, 'Regular Cheese', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (008, 'Pepperoni', 'No');

INSERT INTO HAS_TOPPINGS
VALUES (009, 'Regular Cheese', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (009, 'Pepperoni', 'No');

INSERT INTO PICK_UP
VALUES (004, 1);



-- Order #5
INSERT INTO ORDERS
VALUES (5);

UPDATE CUSTOMER
SET	 ADDRESS = '115 Party Blvd, Anderson SC 29621';

INSERT INTO PIZZA
VALUES (10, 'COMPLETE', '2019-03-05 7:11', 14, 5, '14.50', '5.59');
INSERT INTO PIZZA
VALUES (11, 'COMPLETE', '2019-03-05 7:11', 14, 5, '17.00', '5.59');
INSERT INTO PIZZA
VALUES (12, 'COMPLETE', '2019-03-05 7:11', 14, 5, '14.00', '5.68');

INSERT INTO HAS_TOPPINGS
VALUES (10, 'Pepperoni', 'NO');
INSERT INTO HAS_TOPPINGS
VALUES (10, 'Sausage', 'NO');
INSERT INTO HAS_TOPPINGS
VALUES (10, 'Four Cheese Blend', 'No');

INSERT INTO HAS_TOPPINGS
VALUES (11, 'Ham', 'Yes');
INSERT INTO HAS_TOPPINGS
VALUES (11, 'Pineapple', 'Yes');
INSERT INTO HAS_TOPPINGS
VALUES (11, 'Four Cheese Blend', 'No');

INSERT INTO HAS_TOPPINGS
VALUES (12, 'Jalapenos', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (12, 'Bacon', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (12, 'Four Cheese Blend', 'No');

INSERT INTO ORDER_DISCOUNTS
VALUES (5, 5);

INSERT INTO PIZZA_DISCOUNTS
VALUES (11, 4);

INSERT INTO DELIVERY
VALUES (5, 1);



-- ORDER #6
INSERT INTO ORDERS
VALUES (6);

INSERT INTO CUSTOMER
VALUES (2, 'Matt Engers', NULL, '864-474-9953');

INSERT INTO PIZZA
VALUES (13, 'COMPLETE', '2019-03-02 5:30', 16, 6, '16.85', '7.85');

INSERT INTO HAS_TOPPINGS
VALUES (13, 'Green Pepper', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (13, 'Onion', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (13, 'Roma Tomatoes', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (13, 'Mushrooms', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (13, 'Black Olives', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (13, 'Goat Cheese', 'No');

INSERT INTO PIZZA_DISCOUNTS
VALUES (13, 4);

INSERT INTO PICK_UP
VALUES (6, 2);



-- ORDER #7
INSERT INTO ORDERS
VALUES (7);

INSERT INTO CUSTOMER
VALUES (3, 'Frank Turner', '6745 Wessex St Anderson SC 29621', '864-232-8944');

INSERT INTO PIZZA
VALUES (14, 'COMPLETE', '2019-03-02 6:17', 9, 7, '13.25', '3.20');

INSERT INTO HAS_TOPPINGS
VALUES (14, 'Chicken', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (14, 'Green Pepper', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (14, 'Onion', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (14, 'Mushrooms', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (14, 'Four Cheese Blend', 'No');

INSERT INTO DELIVERY
VALUES (7, 3);



-- ORDER #8
INSERT INTO ORDERS
VALUES (8);

INSERT INTO CUSTOMER
VALUES (5, 'Milo Auckerman', '8879 Suburban Home, Anderson, SC 29621', '864-878-5679');

INSERT INTO PIZZA
VALUES (15, 'COMPLETE', '2019-03-06 8:32', 9, 8, '12.00', '3.75');
INSERT INTO PIZZA
VALUES (16, 'COMPLETE', '2019-03-06 8:32', 9, 8, '12.00', '2.55');

INSERT INTO HAS_TOPPINGS
VALUES (15, 'Four Cheese Blend', 'Yes');

INSERT INTO HAS_TOPPINGS
VALUES (16, 'Regular Cheese', 'No');
INSERT INTO HAS_TOPPINGS
VALUES (16, 'Pepperoni', 'Yes');

INSERT INTO ORDER_DISCOUNTS
VALUES (8, 1);

INSERT INTO DELIVERY
VALUES (8, 5);
