-- Cliente demo
INSERT INTO cliente (id, nombre, apellido, email, password, telefono, direccion, activo)
VALUES (1,'Ana','García','ana@mail.com','1234','3001112233','Calle 10 #1-23', true);

-- Adicionales
INSERT INTO adicional (id, nombre, precio, activo) VALUES
(1,'Queso extra',2000,true),
(2,'Tocineta',3000,true),
(3,'Salsa de la casa',0,true);

-- Productos
INSERT INTO producto (id,nombre,descripcion,precio,img_url,activo) VALUES
(1,'Hamburguesa Clásica','Pan brioche, carne 150g',18000,'/img/platos/inspiracion.png',true),
(2,'Perro Americano','Salchicha + salsas',12000,'/img/personas/chef.png',true),
(3,'Salchipapas','Papas + salchicha',15000,'/img/personas/cocineros.png',true);

-- Relación muchos a muchos
INSERT INTO producto_adicional (producto_id, adicional_id) VALUES
(1,1),(1,2),(1,3),
(2,3),
(3,1),(3,3);