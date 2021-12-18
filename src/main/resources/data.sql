INSERT into USER (id, username,first_name, middle_name,last_name,  email,  password, IS_Enabled, points)  VALUES (111, 'seller','seller','B','Gomez','dean@dean.com','$2y$12$ZULYuC/Cz4RQhK4DngP8s.SfhJA35gNayrpKGZTHCryBvcnKL8u4a',true, 0);
INSERT into USER (id, username,first_name, middle_name, last_name,  email, password, IS_Enabled, points)  VALUES (112, 'buyer','buyer','R','Zaid','zaineh@saineh.com','$2y$12$ZULYuC/Cz4RQhK4DngP8s.SfhJA35gNayrpKGZTHCryBvcnKL8u4a',true,0);
INSERT into USER (id, username, first_name, middle_name, last_name, email, password, IS_Enabled, points)  VALUES (113, 'admin','admin','H','Berhe','zaineh@saineh.com','$2y$12$ZULYuC/Cz4RQhK4DngP8s.SfhJA35gNayrpKGZTHCryBvcnKL8u4a',true,0);

INSERT INTO ROLE(ROLE_ID, name) VALUES (1, 'SELLER');
INSERT INTO ROLE(ROLE_ID, name) VALUES (2, 'BUYER');
INSERT INTO ROLE(ROLE_ID, name) VALUES (3, 'ADMIN');

insert into USER_ROLES(USER_ID, ROLE_ID) values (111, 1);
insert into USER_ROLES(USER_ID, ROLE_ID) values (112, 2);
insert into USER_ROLES(USER_ID, ROLE_ID) values (113, 3);

# Products
insert into product(banner, description, name, price, quantity, sku, user_id)
            values('https://global-uploads.webflow.com/5d556af3fe21d65f602dca94/5d8a50f681e5ab7f71719d2b_77597aba378e1619e666ab94013a046a.png',
                   'Nice Jacket', 'Funcy Jacket', 80, 12, '12345', 111)

insert into product(banner, description, name, price, quantity, sku, user_id)
values('http://ecom.quickwebsitecreation.com/wp-content/uploads/2013/07/Plain-T-Shirt.jpg',
       'T-shirt', 'Awesome', 70, 12, '12345', 112)
