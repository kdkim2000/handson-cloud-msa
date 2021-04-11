INSERT INTO product(id, name, description, picture, price_usd) VALUES ('OLJCESPC7Z','Vintage Typewriter', 'This typewriter looks good in your living room.', '/static/img/products/typewriter.jpg', 'USD|67|990000000');
INSERT INTO product(id, name, description, picture, price_usd) VALUES ('66VCHSJNUP','Vintage Camera Lens', 'You won''t have a camera to use it and it probably doesn''t work anyway.', '/static/img/products/camera-lens.jpg', 'USD|12|490000000');
INSERT INTO product(id, name, description, picture, price_usd) VALUES ('1YMWWN1N4O','Home Barista Kit', 'Always wanted to brew coffee with Chemex and Aeropress at home?', '/static/img/products/barista-kit.jpg', 'USD|124|0');
INSERT INTO product(id, name, description, picture, price_usd) VALUES ('L9ECAV7KIM','Terrarium', 'This terrarium will looks great in your white painted living room.', '/static/img/products/terrarium.jpg', 'USD|36|450000000');
INSERT INTO product(id, name, description, picture, price_usd) VALUES ('2ZYFJ3GM2N','Film Camera', 'This camera looks like it''s a film camera, but it''s actually digital.', '/static/img/products/film-camera.jpg', 'USD|2245|0');
INSERT INTO product(id, name, description, picture, price_usd) VALUES ('0PUK6V6EV0','Vintage Record Player', 'It still works.', '/static/img/products/record-player.jpg', 'USD|65|500000000');
INSERT INTO product(id, name, description, picture, price_usd) VALUES ('LS4PSXUNUM','Metal Camping Mug', 'You probably don''t go camping that often but this is better than plastic cups.', '/static/img/products/camp-mug.jpg', 'USD|24|330000000');
INSERT INTO product(id, name, description, picture, price_usd) VALUES ('9SIQT8TOJO','City Bike', 'This single gear bike probably cannot climb the hills of San Francisco.', '/static/img/products/city-bike.jpg', 'USD|789|500000000');
INSERT INTO product(id, name, description, picture, price_usd) VALUES ('6E92ZMYYFZ','Air Plant', 'Have you ever wondered whether air plants need water? Buy one and figure out.', '/static/img/products/air-plant.jpg', 'USD|12|300000000');

INSERT INTO product_category(product_id, category) VALUES ('OLJCESPC7Z','vintage');
INSERT INTO product_category(product_id, category) VALUES ('66VCHSJNUP','photography');
INSERT INTO product_category(product_id, category) VALUES ('66VCHSJNUP','vintage');
INSERT INTO product_category(product_id, category) VALUES ('1YMWWN1N4O','cookware');
INSERT INTO product_category(product_id, category) VALUES ('L9ECAV7KIM','gardening');
INSERT INTO product_category(product_id, category) VALUES ('2ZYFJ3GM2N','photography');
INSERT INTO product_category(product_id, category) VALUES ('2ZYFJ3GM2N','vintage');
INSERT INTO product_category(product_id, category) VALUES ('0PUK6V6EV0','music');
INSERT INTO product_category(product_id, category) VALUES ('0PUK6V6EV0','vintage');
INSERT INTO product_category(product_id, category) VALUES ('LS4PSXUNUM','cookware');
INSERT INTO product_category(product_id, category) VALUES ('9SIQT8TOJO','cycling');
INSERT INTO product_category(product_id, category) VALUES ('6E92ZMYYFZ','gardening');


/* Inventory Data Insert */
INSERT INTO inventory(id, product_id, product_name, quantity, location) VALUES ( 1, 'OLJCESPC7Z','Vintage Typewriter', 30, 'SEOUL');
INSERT INTO inventory(id, product_id, product_name, quantity, location) VALUES ( 2, '66VCHSJNUP','Vintage Camera' , 30, 'SEOUL');
INSERT INTO inventory(id, product_id, product_name, quantity, location) VALUES ( 3, '1YMWWN1N4O','Home Barista' , 30, 'SEOUL');
INSERT INTO inventory(id, product_id, product_name, quantity, location) VALUES ( 4, 'L9ECAV7KIM','Terrarium' , 30, 'SEOUL');
INSERT INTO inventory(id, product_id, product_name, quantity, location) VALUES ( 5, '2ZYFJ3GM2N','Film Camera' , 30, 'SEOUL');
INSERT INTO inventory(id, product_id, product_name, quantity, location) VALUES ( 6, '0PUK6V6EV0','Vintage Record' , 30, 'SEOUL');
INSERT INTO inventory(id, product_id, product_name, quantity, location) VALUES ( 7, 'LS4PSXUNUM','Metal Camping' , 30, 'SEOUL');
INSERT INTO inventory(id, product_id, product_name, quantity, location) VALUES ( 8, '9SIQT8TOJO','City Bike', 30, 'SEOUL');
INSERT INTO inventory(id, product_id, product_name, quantity, location) VALUES ( 9, '6E92ZMYYFZ','Air Plant', 30, 'SEOUL' );

INSERT INTO my_user(id, email, is_enable, pw, role) VALUES ( '1',	'user1@gmail.com',	false,	'$2a$10$IRTLB5jzGb5vM2A6MDj2B.F.S3MxisKy.8LRHr841ZCkS40.TW9KK',	'ROLE_USER');
INSERT INTO my_user(id, email, is_enable, pw, role) VALUES ( '2',	'user2@gmail.com',	false,	'$2a$10$SrxHrx1bjKQ8yN9pAw03MuptabWlVit19cIXmnwGm65Pwf8VFxPaS',	'ROLE_USER');




