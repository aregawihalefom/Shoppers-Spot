INSERT into USER (id, first_name, username, email,  password, IS_Enabled, points)  VALUES (111, 'Dean','dean','dean@dean.com','$2y$12$ZULYuC/Cz4RQhK4DngP8s.SfhJA35gNayrpKGZTHCryBvcnKL8u4a',true, 0);
INSERT into USER (id, first_name, username, email, password, IS_Enabled, points)  VALUES (112, 'Zaineh','zaineh','zaineh@saineh.com','$2y$12$ZULYuC/Cz4RQhK4DngP8s.SfhJA35gNayrpKGZTHCryBvcnKL8u4a',true,0);
INSERT into USER (id, first_name, username, email, password, IS_Enabled, points)  VALUES (113, 'Aregawi','aregawi','zaineh@saineh.com','$2y$12$ZULYuC/Cz4RQhK4DngP8s.SfhJA35gNayrpKGZTHCryBvcnKL8u4a',true,0);

INSERT INTO ROLE(ROLE_ID, name) VALUES (1, 'SELLER');
INSERT INTO ROLE(ROLE_ID, name) VALUES (2, 'BUYER');
INSERT INTO ROLE(ROLE_ID, name) VALUES (3, 'ADMIN');

insert into USER_ROLES(USER_ID, ROLE_ID) values (111, 1);
insert into USER_ROLES(USER_ID, ROLE_ID) values (112, 2);
insert into USER_ROLES(USER_ID, ROLE_ID) values (113, 3);

insert into order_status(name) value ('ORDER_PLACED');
insert into order_status(name) value ('ORDER_CONFIRMED');
insert into order_status(name) value ('ORDER_CANCELLED');
insert into order_status(name) value ('ORDER_SHIPPED');
insert into order_status(name) value ('ORDER_DELIVERED');
insert into order_status(name) value ('ORDER_RETURNED');

insert into category(name) value ('ORDER_PLACED');
insert into category(name) value ('ORDER_CONFIRMED');
insert into category(name) value ('ORDER_CANCELLED');
insert into category(name) value ('ORDER_SHIPPED');
insert into category(name) value ('ORDER_DELIVERED');
insert into category(name) value ('ORDER_RETURNED');
