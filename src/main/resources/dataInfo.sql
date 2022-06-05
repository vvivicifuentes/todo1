INSERT INTO rol (id, rol_nombre) VALUES (1, 'ROLE_USER');
INSERT INTO rol (id, rol_nombre) VALUES (2, 'ROLE_ADMIN');

INSERT INTO super_hero (id,editorial,name) VALUES (1,'SUPER MAN','DC COMICS');

INSERT INTO producto (id,name,price,stock,super_hero_id,status) VALUES (1,'CAMISETA DE IRON-MAN',30500,50,3,'CREATED');
INSERT INTO producto (id,name,price,stock,super_hero_id,status) VALUES (2,'VASO DE SUPER MAN',15000,10,1,'CREATED');

INSERT INTO invoices (id,number_invoice,description,state) VALUES (1,'001-2022','VENTA','CREATED');

INSERT INTO invoice_items(id,quantity,price,product_id,invoice_id) VALUES (1,3,45000,2,1);
