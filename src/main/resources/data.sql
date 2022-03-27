INSERT into USER (id, username,first_name, middle_name,last_name,  email,  password, IS_Enabled, points)  VALUES (111, 'seller','seller','B','Gomez','dean@dean.com','$2y$12$ZULYuC/Cz4RQhK4DngP8s.SfhJA35gNayrpKGZTHCryBvcnKL8u4a',true, 0);
INSERT into USER (id, username,first_name, middle_name, last_name,  email, password, IS_Enabled, points)  VALUES (112, 'buyer','buyer','R','Zaid','zaineh@saineh.com','$2y$12$ZULYuC/Cz4RQhK4DngP8s.SfhJA35gNayrpKGZTHCryBvcnKL8u4a',true,0);
INSERT into USER (id, username, first_name, middle_name, last_name, email, password, IS_Enabled, points)  VALUES (113, 'admin','admin','H','Berhe','zaineh@saineh.com','$2y$12$ZULYuC/Cz4RQhK4DngP8s.SfhJA35gNayrpKGZTHCryBvcnKL8u4a',true,0);

INSERT INTO ROLE(ROLE_ID, name) VALUES (1, 'ROLE_SELLER');
INSERT INTO ROLE(ROLE_ID, name) VALUES (2, 'ROLE_BUYER');
INSERT INTO ROLE(ROLE_ID, name) VALUES (3, 'ROLE_ADMIN');

insert into USER_ROLES(USER_ID, ROLE_ID) values (111, 1);
insert into USER_ROLES(USER_ID, ROLE_ID) values (112, 2);
insert into USER_ROLES(USER_ID, ROLE_ID) values (113, 3);

insert into product(banner, description, name, price, quantity, sku, user_id)
            values('https://global-uploads.webflow.com/5d556af3fe21d65f602dca94/5d8a50f681e5ab7f71719d2b_77597aba378e1619e666ab94013a046a.png',
                   'Nice Jacket', 'Funcy Jacket', 80, 12, '12345', 111);
insert into product(banner, description, name, price, quantity, sku, user_id)
values('http://ecom.quickwebsitecreation.com/wp-content/uploads/2013/07/Plain-T-Shirt.jpg',
       'T-shirt', 'Awesome', 70, 12, '12345', 112);

insert into product(banner, description, name, price, quantity, sku, user_id)
values('https://i.pinimg.com/originals/f5/31/3f/f5313f65b0b63b4ad4f018e94e0b50e8.jpg',
       'Coat', 'Comfy', 120, 12, '34634', 111);

insert into product(banner, description, name, price, quantity, sku, user_id)
values('https://i5.walmartimages.com/asr/e160b396-d807-4199-a21f-c38c6f0cdb49_1.8c0682269ea2cda0a3fd3ed30e5c71a9.jpeg',
       'Very Comfortable and Stretchable Pants', 'Jean Pants', 90, 23, '2352345234', 111);
insert into product(banner, description, name, price, quantity, sku, user_id)
values('https://media.glamour.com/photos/6091783294d91bbf997a2f2b/master/w_1600,h_2148,c_limit/cute%20summer%20outfits.jpeg',
       'Summer Fits', 'Slick Summer', 100, 10, '2352345234', 111);
insert into product(banner, description, name, price, quantity, sku, user_id)
values('https://bluesoleshoes.com/wp-content/uploads/2021/03/3204129-copy-1.jpg',
       'Shoes', 'Shoes', 150, 2, '2352345234', 111);

